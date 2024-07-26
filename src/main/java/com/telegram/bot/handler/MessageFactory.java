package com.telegram.bot.handler;

import com.telegram.bot.constants.BotCommand;
import com.telegram.bot.constants.Constants;
import java.util.Optional;
import java.util.WeakHashMap;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageFactory {

  private final MessageSender sender;
  private final WeakHashMap<Long, Boolean> userSession = new WeakHashMap<>();

  public MessageFactory(MessageSender sender) {
    this.sender = sender;
  }

  public void start(long chatId) {
    userSession.put(chatId, true);
    try {
      sender.execute(SendMessage.builder()
          .chatId(chatId)
          .text(Constants.START_REPLY.getText())
          .replyMarkup(KeyboardFactory.startButtons())
          .build());
    } catch (TelegramApiException e) {
      throw new RuntimeException(e);
    }
  }

  private void stopChat(long chatId) {
    userSession.remove(chatId);
    try {
      sender.execute(SendMessage.builder()
          .chatId(chatId)
          .text("Thank you for your time. See you soon!\nPress /start to order again")
          .replyMarkup(new ReplyKeyboardRemove(true))
          .build());
    } catch (TelegramApiException e) {
      throw new RuntimeException(e);
    }
  }

  public void replyToButtons(long chatId, Message message) {
    BotCommand command = Optional.ofNullable(
            BotCommand.fromCommandName(message.getText().replaceAll("/", "")))
        .orElseThrow(() -> new RuntimeException("No command Found"));
    if (BotCommand.STOP.equals(command)) {
      stopChat(chatId);
    }

    switch (command) {
      case DICTIONARY -> {
        try {
          sender.execute(SendMessage.builder()
              .chatId(chatId)
              .text("To be Coming Dictionary")
              .replyMarkup(KeyboardFactory.startButtons())
              .build());
        } catch (TelegramApiException e) {
          throw new RuntimeException(e);
        }
      }
      case QUIZZES -> {
        try {
          sender.execute(SendMessage.builder()
              .chatId(chatId)
              .text("To be Coming Quizzes")
              .replyMarkup(KeyboardFactory.startButtons())
              .build());
        } catch (TelegramApiException e) {
          throw new RuntimeException(e);
        }

      }
    }

  }

  public boolean isUserActive(long chatId) {
    return userSession.containsKey(chatId);
  }
}
