package com.telegram.bot.handler;

import com.telegram.bot.constants.BotCommand;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KeyboardFactory {

  public static ReplyKeyboardMarkup startButtons() {
    KeyboardRow row = new KeyboardRow();
    row.add(BotCommand.UPDATE_DICTIONARY.getCommandName());
    row.add(BotCommand.CREATE_QUIZZES.getCommandName());
    return new ReplyKeyboardMarkup(List.of(row));
  }

  public static ReplyKeyboardMarkup quizNumberOfWordsButtons() {
    KeyboardRow row = new KeyboardRow();
    row.add(BotCommand.GET_WORD.getCommandName());
    row.add(BotCommand.MAIN_MENU.getCommandName());
    return new ReplyKeyboardMarkup(List.of(row));
  }
}
