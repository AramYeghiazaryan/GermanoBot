package com.telegram;

import com.telegram.bot.service.DictionaryService;
import com.telegram.duolingo.model.LearnedWordsResponse;
import com.telegram.duolingo.model.UserData;
import com.telegram.duolingo.service.DuolingoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class GermanoBotApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(GermanoBotApplication.class);
    DuolingoService duolingoService = context.getBean(DuolingoService.class);
    DictionaryService dictionaryService = context.getBean(DictionaryService.class);

    UserData userData = duolingoService.getUserData();
    LearnedWordsResponse learnedWordsResponse = duolingoService.getPracticedLexemes(
        userData.getProgressedSkills());

    dictionaryService.updateDictionaryFile(learnedWordsResponse);
  }

  private void startBot() {

//    ApiContextInitializer.init();
//    TelegramBotsApi botsApi = new TelegramBotsApi();
//    try {
//      botsApi.registerBot(new GermanoBot());
//    } catch (TelegramApiException e) {
//      e.printStackTrace();
//    }
  }
}
