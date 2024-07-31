package com.telegram.bot;

import static org.telegram.abilitybots.api.util.AbilityUtils.getChatId;

import com.telegram.bot.constants.BotCommand;
import com.telegram.bot.handler.KeyboardFactory;
import com.telegram.bot.handler.MessageFactory;
import com.telegram.bot.service.DictionaryService;
import com.telegram.bot.service.QuizService;
import java.util.List;
import java.util.Optional;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class GermanoBot extends TelegramLongPollingBot {

  private static final String BOT_TOKEN = "7277366517:AAEeLitCCl4pwRHpA3tFPMfN3QCA0vLVbcc";
  private static final String BOT_USERNAME = "Learn_DeutschBot";
  private final MessageFactory messageFactory;

  public GermanoBot(ApplicationContext context) {
    super(BOT_TOKEN);
    messageFactory = new MessageFactory(
        context.getBean(DictionaryService.class),
        context.getBean(QuizService.class)
    );
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      try {
        Message message = update.getMessage();
        BotCommand command = Optional.ofNullable(
                BotCommand.fromCommandName(message.getText().replaceAll("/", "")))
            .orElse(BotCommand.QUIZ_ANSWER);

        if (BotCommand.QUIZ_ANSWER.equals(command)) {
          executeQuizAnswerResponse(update, message);
        } else {
          execute(messageFactory.replyToButtonsWithText(getChatId(update), command));
        }
      } catch (TelegramApiException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void executeQuizAnswerResponse(Update update, Message message) throws TelegramApiException {
    List<InputMedia> responseAudios = messageFactory.sendQuizAudioAnswer(message);
    if (responseAudios.size() == 1) {
      InputMedia inputMedia = responseAudios.get(0);
      execute(SendAudio.builder()
          .chatId(getChatId(update))
          .caption(inputMedia.getCaption())
          .audio(new InputFile(inputMedia.getMedia()))
          .replyMarkup(KeyboardFactory.quizNumberOfWordsButtons())
          .build());
    } else {
      execute(SendMediaGroup.builder()
          .medias(responseAudios)
          .chatId(getChatId(update))
          .build());
      execute(SendMessage.builder()
          .replyMarkup(KeyboardFactory.quizNumberOfWordsButtons())
          .build());
    }
  }

  @Override
  public String getBotUsername() {
    return BOT_USERNAME;
  }
}