package com.telegram.bot.constants;

import lombok.Getter;

public enum BotCommand {
  UPDATE_DICTIONARY("update dictionary"),
  CREATE_QUIZZES("create a quiz"),
  START("start"),
  STOP("stop"),
  GET_WORD("get word"),
  MAIN_MENU("main menu"),
  QUIZ_ANSWER("quiz answer");

  @Getter
  private final String commandName;

  BotCommand(String commandName) {
    this.commandName = commandName;
  }

  public static BotCommand fromCommandName(String commandName) {
    for (BotCommand command : BotCommand.values()) {
      if (command.commandName.equals(commandName)) {
        return command;
      }
    }
    return null;
  }
}
