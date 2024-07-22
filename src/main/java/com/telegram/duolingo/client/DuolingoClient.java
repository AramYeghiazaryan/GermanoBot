package com.telegram.duolingo.client;

import com.telegram.duolingo.model.LearnedWordsResponse;
import com.telegram.duolingo.model.LexemesCountResponse;
import com.telegram.duolingo.model.UserResponse;
import feign.RequestLine;

public interface DuolingoClient {

  @RequestLine("GET /2017-06-30/users/1508386556")
  UserResponse getUserData();

  @RequestLine("POST /2017-06-30/users/1508386556/courses/de/en/learned-lexemes/count")
  LexemesCountResponse getLearnedLexemesCount(String requestBody);

  //TODO make autoPagination to load all
  @RequestLine("POST /2017-06-30/users/1508386556/courses/de/en/learned-lexemes?limit=100&sortBy=LEARNED_DATE&startIndex=0")
  LearnedWordsResponse getLearnedLexemes(String requestBody);
}
