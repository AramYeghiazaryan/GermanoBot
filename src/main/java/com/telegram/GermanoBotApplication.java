package com.telegram;

import com.telegram.bot.GermanoBot;
import com.telegram.bot.service.DictionaryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@EnableFeignClients
public class GermanoBotApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(GermanoBotApplication.class, args);
    try {
      TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
      botsApi.registerBot(ctx.getBean(GermanoBot.class, AbilityBot.class    ));
    } catch (TelegramApiException e) {
      throw new RuntimeException(e);
    }
  }
}
