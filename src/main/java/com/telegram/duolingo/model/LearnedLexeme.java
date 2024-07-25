package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class LearnedLexeme {
  @JsonProperty("text")
  private String text;
  @JsonProperty("translations")
  private List<String> translations;
  @JsonProperty("audioURL")
  private String audioURL;
  @JsonProperty("isNew")
  private Boolean isNew;
}
