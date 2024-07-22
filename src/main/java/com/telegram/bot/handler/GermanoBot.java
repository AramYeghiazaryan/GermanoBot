package com.telegram.bot.handler;

import static com.telegram.bot.model.BotCommand.DICTIONARY;
import static com.telegram.bot.model.BotCommand.QUIZZES;

import com.telegram.bot.model.BotCommand;
import com.telegram.bot.service.DictionaryService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class GermanoBot extends TelegramLongPollingBot {

  private static final String BOT_TOKEN = "7277366517:AAEeLitCCl4pwRHpA3tFPMfN3QCA0vLVbcc";
  private static final String BOT_USERNAME = "Learn_DeutschBot";
  private static final DictionaryService dictionaryService = new DictionaryService();

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      String messageText = update.getMessage()
          .getText()
          .replaceAll("/", "");
      long chatId = update.getMessage().getChatId();

      BotCommand command = BotCommand.fromCommandName(messageText);
      sendResponse(processMessage(command, chatId));
    }
  }

  private static SendMessage processMessage(BotCommand command, long chatId) {
    SendMessage response = new SendMessage()
        .setChatId(chatId)
        .setText("no such command");
    if (command == null) {
      return response;
    } else if (command.equals(QUIZZES)) {
      response = new SendMessage()
          .setChatId(chatId)
          .setText("coming soon Quizzes");
    } else if (command.equals(DICTIONARY)) {
      response = dictionaryService.getDictionaryTable(chatId);
    }

    return response;
  }

  private void sendResponse(SendMessage response) {
    try {
      execute(response);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String getBotUsername() {
    return BOT_USERNAME;
  }

  @Override
  public String getBotToken() {
    return BOT_TOKEN;
  }
}