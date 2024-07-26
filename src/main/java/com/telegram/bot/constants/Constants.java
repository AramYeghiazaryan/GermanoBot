package com.telegram.bot.constants;

import lombok.Getter;

public enum Constants {
  START_DESCRIPTION("Hallo"),
  START_REPLY("Start using the telegram bot if you want to check your German");

  @Getter
  private String text;

  Constants(String value) {
    this.text = value;

  }
}
