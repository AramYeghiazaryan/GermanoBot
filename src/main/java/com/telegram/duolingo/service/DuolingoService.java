package com.telegram.duolingo.service;

import com.telegram.duolingo.client.DuolingoClient;
import com.telegram.duolingo.model.GenericId;
import com.telegram.duolingo.model.LearnedWordsResponse;
import com.telegram.duolingo.model.LexemesCountResponse;
import com.telegram.duolingo.model.PathSectioned;
import com.telegram.duolingo.model.ProgressedSkill;
import com.telegram.duolingo.model.UnitState;
import com.telegram.duolingo.model.Units;
import com.telegram.duolingo.model.UserData;
import com.telegram.duolingo.model.UserResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DuolingoService {

  private final DuolingoClient duolingoClient;

  public UserData getUserData() {
    UserResponse userResponse = duolingoClient.getUserData();

    return UserData.builder()
        .currentCourse(userResponse.getCurrentCourse())
        .progressedSkills(constructProgressedSkills(userResponse))
        .username(userResponse.getUsername())
        .build();
  }

  private static Set<ProgressedSkill> constructProgressedSkills(UserResponse userResponse) {
    return userResponse.getCurrentCourse()
        .getPathSectioned()
        .stream()
        .map(PathSectioned::getUnits)
        .flatMap(Collection::stream)
        .map(Units::getLevels)
        .flatMap(Collection::stream)
        .filter(level -> UnitState.isValidState(level.getState()))
        .map(level -> {
          int finishedLevels = UnitState.PASSED.getState().equals(level.getState()) ? 1 : 0;
          return ProgressedSkill.builder()
              .finishedLevels(finishedLevels)
              .finishedSessions(level.getFinishedSessions())
              .skillId(GenericId.builder()
                  .id(level.getPathLevelMetadata().getSkillId())
                  .build())
              .build();
        })
        .filter(progressedSkill -> Objects.nonNull(progressedSkill.getSkillId().getId()))
        .collect(Collectors.toSet());
  }

  public LearnedWordsResponse getPracticedLexemes(Set<ProgressedSkill> progressedSkills) {
    LexemesCountResponse lexemesCountResponse = getLexemesCount(progressedSkills);
    return duolingoClient.getLearnedLexemes(
        "100",
        "LEARNED_DATE",
        "0",
        Map.of(
            "lastTotalLexemeCount", lexemesCountResponse.getLexemeCount(),
            "progressedSkills", progressedSkills
        )
    );
  }

  public LexemesCountResponse getLexemesCount(Set<ProgressedSkill> progressedSkills) {
    return duolingoClient.getLearnedLexemesCount(Map.of("progressedSkills", progressedSkills));
  }
}
