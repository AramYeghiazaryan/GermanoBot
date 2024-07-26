package com.telegram.bot.constants;

public enum BotCommand {
  UPDATE_DICTIONARY("update dictionary"),
  CREATE_QUIZZES("create a quiz"),
  START("start"),
  STOP("stop");

  public final String commandName;

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
