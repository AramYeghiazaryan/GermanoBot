package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Lexeme {
  @JsonProperty("lexemeId")
  private String lexemeId;

  @JsonProperty("word")
  private String word;

  @JsonProperty("translation")
  private String translation;

  @JsonProperty("isNew")
  private boolean isNew;
}
