package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Units {
  @JsonProperty("unitIndex")
  private int unitIndex;
  @JsonProperty("levels")
  private List<Level> levels;
}
