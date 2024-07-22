package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Explanation {
  @JsonProperty("intro")
  private String intro;

  @JsonProperty("title")
  private String title;

  @JsonProperty("url")
  private String url;
}
