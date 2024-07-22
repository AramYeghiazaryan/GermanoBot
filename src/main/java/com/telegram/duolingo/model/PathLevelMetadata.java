package com.telegram.duolingo.model;

import lombok.Data;

@Data
public class PathLevelMetadata {
  private String skillId;
  private Integer crownLevelIndex; // Can be null
  private String anchorSkillId; // Can be null
  private Integer indexSinceAnchorSkill; // Can be null
  private String treeId; // Can be null
  private Integer unitIndex; // Can be null
}
