package com.telegram.bot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class DictionaryService {

  private static final ObjectMapper MAPPER = new ObjectMapper().configure(
      SerializationFeature.INDENT_OUTPUT, true);
  ;

  public SendMessage getDictionaryTable(long chatId) {
    URL url = getClass().getClassLoader().getResource("dictionary.md");
    SendMessage message = new SendMessage();
    if (url == null) {
      System.err.println("File not found in resources folder");
      return message;
    }
    List<Map<String, String>> dictionary = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(
        new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
      String line;
      br.readLine();
      br.readLine();

      while ((line = br.readLine()) != null) {
        dictionary.add(processEachLine(line));
      }

      message = new SendMessage()
          .setChatId(chatId)
          .setText(MAPPER.writeValueAsString(dictionary));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return message;
  }

  private LinkedHashMap<String, String> processEachLine(String line) {
    String[] words = Stream.of(line.split("\\|"))
        .filter(word -> !word.isBlank())
        .map(String::trim)
        .toArray(String[]::new);

    LinkedHashMap<String, String> result = new LinkedHashMap<>();
    result.put("German", words[0]);
    result.put("English", words[1]);
    result.put("Pronunciation", words[2]);
    result.put("Way of Saying", words[3]);
    return result;
  }
}
