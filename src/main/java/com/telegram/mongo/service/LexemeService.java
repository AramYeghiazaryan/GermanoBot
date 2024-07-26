package com.telegram.mongo.service;


import com.telegram.mongo.entity.LexemeEntity;
import com.telegram.mongo.repository.LexemeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LexemeService {

  private final LexemeRepository lexemeRepository;

  public List<LexemeEntity> getAll() {
    return lexemeRepository.findAll();
  }

  public List<LexemeEntity> saveAll(List<LexemeEntity> lexemes) {
    return lexemeRepository.saveAll(lexemes);
  }

  public LexemeEntity save(LexemeEntity lexeme) {
    return lexemeRepository.save(lexeme);
  }

  public LexemeEntity getById(String id) {
    return lexemeRepository.findById(id).orElse(null);
  }
}
