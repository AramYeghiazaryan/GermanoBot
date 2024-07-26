package com.telegram.mongo.service;


import com.telegram.duolingo.model.Lexeme;
import com.telegram.mongo.repository.LexemeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;

@Service
@RequiredArgsConstructor
public class LexemeService {

  private final LexemeRepository lexemeRepository;

  public List<Lexeme> getAll() {
    return lexemeRepository.findAll();
  }

  public Lexeme save(Lexeme lexeme) {
    return lexemeRepository.save(lexeme);
  }

  public Lexeme getById(String id) {
    return lexemeRepository.findById(id).orElse(null);
  }
}
