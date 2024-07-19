package com.telegram;

import com.telegram.bot.GermanoBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

  public static void main(String[] args) {
    startBot();
  }

  private static void startBot() {
    // Initialize Api Context
    ApiContextInitializer.init();

    // Instantiate Telegram Bots API
    TelegramBotsApi botsApi = new TelegramBotsApi();

    // Register our bot
    try {
      botsApi.registerBot(new GermanoBot());
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }
}
