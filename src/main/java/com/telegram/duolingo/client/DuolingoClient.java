package com.telegram.duolingo.client;

import com.telegram.duolingo.model.LearnedWordsResponse;
import com.telegram.duolingo.model.LexemesCountResponse;
import com.telegram.duolingo.model.ProgressedSkill;
import com.telegram.duolingo.model.UserResponse;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "duolingoClient", url = "https://api.duolingo.com/2017-06-30")
public interface DuolingoClient {

  @GetMapping("/users/1508386556")
  UserResponse getUserData();

  @PostMapping(value = "/users/1508386556/courses/de/en/learned-lexemes/count",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  LexemesCountResponse getLearnedLexemesCount(
      @RequestBody Map<String, Set<ProgressedSkill>> requestBody);

  @PostMapping(value = "/users/1508386556/courses/de/en/learned-lexemes")
  LearnedWordsResponse getLearnedLexemes(
      @RequestParam("limit") String limit,
      @RequestParam("sortBy") String sortBy,
      @RequestParam("startIndex") String startIndex,
      @RequestBody Map<String, Object> requestBody
  );
}