package com.telegram.bot.handler;

import com.telegram.bot.constants.BotCommand;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyboardFactory {

  public static ReplyKeyboardMarkup startButtons() {
    KeyboardRow row = new KeyboardRow();
    row.add(BotCommand.UPDATE_DICTIONARY.commandName);
    row.add(BotCommand.CREATE_QUIZZES.commandName);
    return new ReplyKeyboardMarkup(List.of(row));
  }
}
