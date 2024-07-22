package com.telegram.duolingo.model;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {

  private CurrentCourse currentCourse;
  private String username;
  private Set<ProgressedSkill> progressedSkills;
}
