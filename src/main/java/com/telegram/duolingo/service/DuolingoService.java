package com.telegram.duolingo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import feign.Feign;
import feign.jackson.JacksonDecoder;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class DuolingoService {

  private static final String AUTHORIZATION_HEADER_VALUE = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjYzMDcyMDAwMDAsImlhdCI6MCwic3ViIjoxNTA4Mzg2NTU2fQ._BUa8va5fCPZeKNiVNGKhcYJyxYYpX2pioT_h-Ug1Yo";
  private static final String COOKIE_HEADER_VALUE = "wuuid=e1c38a2a-1d74-4a45-81d4-9da65f94eeed; lang=en; lu=https://www.duolingo.com/practice-hub/words; initial_referrer=$direct; lr=; _gcl_au=1.1.2084406218.1721647546; _gid=GA1.2.383533937.1721647546; _fbp=fb.1.1721647545838.506082287480924200; wuuid=ef44e3c4-c455-441d-a035-270a499bd56d; g_state={\"i_l\":0}; csrf_token=ImM4NDI5YjU2ZGJjMjQ0ZTRiOGU5MWU3MGIxZDkwNjIwIg==; logged_out_uuid=1508386556; logged_in=true; lp=N/A; __gads=ID=bcc7a233f70c7ab4:T=1721647577:RT=1721648600:S=ALNI_Mbio2ia2x4Q5S88fTfV8g6K7o0qBQ; __gpi=UID=00000e9b6cd7667a:T=1721647577:RT=1721648600:S=ALNI_MZw-den3zW9bqIj2yD70Cc4vsw_Xg; __eoi=ID=d4d201ed1293e668:T=1721647577:RT=1721648600:S=AA-AfjbSPaKZXcSBhHMcazuuLX6e; OptanonConsent=isGpcEnabled=0&datestamp=Mon+Jul+22+2024+15%3A47%3A01+GMT%2B0400+(Armenia+Standard+Time)&version=202404.1.0&browserGpcFlag=0&isIABGlobal=false&hosts=&consentId=73b4cfcc-19ed-4835-b6a3-67acfabcc67c&interactionCount=1&isAnonUser=1&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1%2CC0004%3A1&AwaitingReconsent=false; _ga=GA1.2.1672735129.1721647546; _gat_UA-21595814-1=1; FCNEC=%5B%5B%22AKsRol8FfVAg_R0gvH0F-MMGRAcwNZxy1bWcCcvc_cMu2Yyd83YdTlugMUNEMWqIkyUifUAvGFDlHv5MTwAb0HpcIB0zdh_4Z4bYWVPLkBWm9qu2pxQRXmWVIvtcfvpZSAuvE3AQcr5WUeYZSj5hOxtiErfz1Tpe5g%3D%3D%22%5D%5D; AWSALB=JMpzqKhY8TXrY78nwhaPJ+suq3H9+E9SQ7BK1orNFakXioDN6Lhd+7Wp5X1Ime6bFnswBIyu5jIOz/m469QraLFLHDev0QGFNhPPtPdyDwAcXqSKVkuNtrgwk6Oj; AWSALBCORS=JMpzqKhY8TXrY78nwhaPJ+suq3H9+E9SQ7BK1orNFakXioDN6Lhd+7Wp5X1Ime6bFnswBIyu5jIOz/m469QraLFLHDev0QGFNhPPtPdyDwAcXqSKVkuNtrgwk6Oj; _ga_CSFDVCPQ4F=GS1.1.1721647545.1.1.1721648883.48.0.0; jwt_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjYzMDcyMDAwMDAsImlhdCI6MCwic3ViIjoxNTA4Mzg2NTU2fQ._BUa8va5fCPZeKNiVNGKhcYJyxYYpX2pioT_h-Ug1Yo; tsl=1721648883475; AWSALB=0MXhqlc6KE/ViCqOr2H06nob6LYDskO2K53nYQb0Bd5rvGDzpPly5oPySGlfO1LWz395HyB5LpsmUFR8S3lUXy9Si9q2osjRzonkNH4QvnuzWLrKl2guTpjb/+G2; AWSALBCORS=0MXhqlc6KE/ViCqOr2H06nob6LYDskO2K53nYQb0Bd5rvGDzpPly5oPySGlfO1LWz395HyB5LpsmUFR8S3lUXy9Si9q2osjRzonkNH4QvnuzWLrKl2guTpjb/+G2";
  private static final ObjectMapper MAPPER = new ObjectMapper();

  public UserData getUserData() {
    DuolingoClient client = constructFeignClient();
    UserResponse userResponse = client.getUserData();

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

    DuolingoClient client = constructFeignClient();
    LearnedWordsResponse learnedWordsResponse = null;
    try {
      learnedWordsResponse = client.getLearnedLexemes(
          MAPPER.writeValueAsString(Map.of(
                  "lastTotalLexemeCount", lexemesCountResponse.getLexemeCount(),
                  "progressedSkills", progressedSkills
              )
          )
      );
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return learnedWordsResponse;
  }

  public LexemesCountResponse getLexemesCount(Set<ProgressedSkill> progressedSkills) {
    DuolingoClient client = constructFeignClient();

    LexemesCountResponse lexemesCountResponse = null;
    try {
      lexemesCountResponse = client.getLearnedLexemesCount(
          MAPPER.writeValueAsString(Map.of("progressedSkills", progressedSkills))
      );
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return lexemesCountResponse;
  }

  private DuolingoClient constructFeignClient() {
    return Feign.builder()
        .requestInterceptor(template -> {
          template.header("accept", "application/json; charset=UTF-8");
          template.header("content-type", "application/json; charset=UTF-8");
          template.header("accept-language", "en-US,en;q=0.9");
          template.header("authorization", AUTHORIZATION_HEADER_VALUE);
          template.header("cookie", COOKIE_HEADER_VALUE);
        })
        .decoder(new JacksonDecoder())
        .target(DuolingoClient.class, "https://www.duolingo.com");
  }

}