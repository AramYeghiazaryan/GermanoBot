package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CurrentCourse {
  @JsonProperty("skills")
  private List<List<Skill>> skills;
  @JsonProperty("pathSectioned")
  private List<PathSectioned> pathSectioned;
}
