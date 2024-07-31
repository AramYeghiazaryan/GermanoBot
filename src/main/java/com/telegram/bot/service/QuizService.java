package com.telegram.bot.service;

import com.telegram.mongo.entity.LexemeEntity;
import com.telegram.mongo.repository.CustomLexemeRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {

  private final CustomLexemeRepository customLexemeRepository;
  private final Map<String, LexemeEntity> wordsTranslationsMap = new HashMap<>();

  public String createQuiz() {
    wordsTranslationsMap.clear();
    LexemeEntity randomLexemes = customLexemeRepository.getRandomWordsFromDictionary();
    populateWordsTranslationsMap(randomLexemes);
    return randomLexemes.getTranslation();
  }

  private void populateWordsTranslationsMap(LexemeEntity randomLexeme) {
    wordsTranslationsMap.put(randomLexeme.getWord().toLowerCase(), randomLexeme);
  }

  public List<LexemeEntity> checkAnswer(String answer) {
    List<String> translations = new ArrayList<>(Arrays.asList(answer.split(",")));
    // Using an iterator to safely remove elements during iteration
    Iterator<String> iterator = translations.iterator();
    while (iterator.hasNext()) {
      String translation = iterator.next();
      LexemeEntity lexemeEntity = wordsTranslationsMap.get(translation.toLowerCase());
      if (lexemeEntity != null && answer.equalsIgnoreCase(lexemeEntity.getWord())) {
        wordsTranslationsMap.remove(translation);
        iterator.remove(); // Safely remove element from translations list
      }
    }

    return new ArrayList<>(wordsTranslationsMap.values());
  }
}
