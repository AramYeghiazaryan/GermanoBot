package com.telegram.bot.handler;

import com.telegram.bot.constants.BotCommand;
import com.telegram.bot.constants.Constants;
import com.telegram.bot.service.DictionaryService;
import com.telegram.bot.service.QuizService;
import com.telegram.mongo.entity.LexemeEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAudio;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@RequiredArgsConstructor
public class MessageFactory {

  private final DictionaryService dictionaryService;
  private final QuizService quizService;
  private final String SUPER_AUDIO_URL = "https://d1vq87e9lcf771.cloudfront.net/beade/f66d3882363f0a7fb21f157768306126";

  public SendMessage start(long chatId) {
    return SendMessage.builder()
        .chatId(chatId)
        .text(Constants.START_REPLY.getText())
        .replyMarkup(KeyboardFactory.startButtons())
        .build();
  }

  public SendMessage replyToButtonsWithText(long chatId, BotCommand command) {
    return switch (command) {
      case START, MAIN_MENU -> start(chatId);
      case STOP -> handleStopChatRequest(chatId);
      case UPDATE_DICTIONARY -> handleUpdateDictionaryRequest(chatId);
      case CREATE_QUIZZES -> handleCreateQuizzesRequest(chatId);
      case GET_WORD -> {
        String translations = quizService.createQuiz();
        yield SendMessage.builder()
            .chatId(chatId)
            .text(String.format("""
                    *%s*
                    please write German translation of this word
                    """,
                translations))
            .replyMarkup(new ReplyKeyboardRemove(true))
            .parseMode(ParseMode.MARKDOWN)
            .build();
      }
      default -> null;
    };

  }

  public List<InputMedia> sendQuizAudioAnswer(Message message) {
    List<LexemeEntity> wrongAnswers = quizService.checkAnswer(message.getText());
    if (wrongAnswers.isEmpty()) {
      return List.of(InputMediaAudio.builder()
          .caption("Super Gut!")
          .media(SUPER_AUDIO_URL)
          .title("Super")
          .build());
    }
    return wrongAnswers.stream()
        .map(wrongAnswerLexeme -> InputMediaAudio.builder()
            .caption(String.format("""
                    You answered wrong the correct answer is:
                    German: %s
                    English: %s
                    """,
                wrongAnswerLexeme.getWord(),
                wrongAnswerLexeme.getTranslation()
            ))
            .media(wrongAnswerLexeme.getAudioURL())
            .title("Correct Answer")
            .build()
        ).collect(Collectors.toList());
  }

  private SendMessage handleStopChatRequest(long chatId) {
    return SendMessage.builder()
        .chatId(chatId)
        .text("Thank you for your time. See you soon!\nPress /start to order again")
        .replyMarkup(new ReplyKeyboardRemove(true))
        .build();
  }

  private SendMessage handleUpdateDictionaryRequest(long chatId) {
    int updatedEntitiesNumber = dictionaryService.updateDictionary().size();
    return SendMessage.builder()
        .chatId(chatId)
        .text(String.format("Updated Dictionary with number of words %s",
            updatedEntitiesNumber))
        .replyMarkup(KeyboardFactory.startButtons())
        .build();
  }

  private SendMessage handleCreateQuizzesRequest(long chatId) {
    return SendMessage.builder()
        .chatId(chatId)
        .text("Choose Number of words which should be included in the Quiz or Return to Main Menu")
        .replyMarkup(KeyboardFactory.quizNumberOfWordsButtons())
        .build();
  }
}
