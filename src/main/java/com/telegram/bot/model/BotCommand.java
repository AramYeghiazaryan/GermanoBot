package com.telegram.bot.model;

public enum BotCommand {
  DICTIONARY("dictionary"),
  QUIZZES("quiz");

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
