package com.telegram.duolingo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProgressedSkill {

  Integer finishedLevels;
  Integer finishedSessions;
  GenericId skillId;
}
