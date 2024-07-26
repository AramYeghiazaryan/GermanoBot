package com.telegram.bot.service;

import com.telegram.duolingo.model.LearnedWordsResponse;
import com.telegram.duolingo.model.UserData;
import com.telegram.duolingo.service.DuolingoService;
import com.telegram.mongo.entity.LexemeEntity;
import com.telegram.mongo.mapper.LexemeMapper;
import com.telegram.mongo.service.LexemeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DictionaryService {

  private final DuolingoService duolingoService;
  private final LexemeService lexemeService;
  private final LexemeMapper lexemeMapper = LexemeMapper.INSTANCE;

  public List<LexemeEntity> updateDictionary() {
    final UserData userData = duolingoService.getUserData();

    final LearnedWordsResponse learnedWordsResponse = duolingoService.getPracticedLexemes(
        userData.getProgressedSkills());

    List<LexemeEntity> lexemeEntities = lexemeMapper.learnedLexemesToLexemeEntities(
        learnedWordsResponse.getLearnedLexemes());
    return lexemeService.saveAll(lexemeEntities);
  }
}
