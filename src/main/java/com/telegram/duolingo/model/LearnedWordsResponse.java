package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class LearnedWordsResponse {
  @JsonProperty("learnedLexemes")
  private List<LearnedLexeme> learnedLexemes;
  @JsonProperty("pagination")
  private Pagination pagination;
}
