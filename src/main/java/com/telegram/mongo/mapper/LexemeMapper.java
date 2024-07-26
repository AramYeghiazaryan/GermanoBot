package com.telegram.mongo.mapper;

import com.telegram.duolingo.model.LearnedLexeme;
import com.telegram.duolingo.model.UnitState;
import com.telegram.mongo.entity.LexemeEntity;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LexemeMapper {

  LexemeMapper INSTANCE = Mappers.getMapper(LexemeMapper.class);
  String MAP_TRANSLATIONS_METHOD_NAME = "mapTranslations";
  String MAP_LEXEME_ID_METHOD_NAME = "mapLexemeId";

  @Mapping(source = "text", target = "word")
  @Mapping(source = "translations", target = "translation", qualifiedByName = MAP_TRANSLATIONS_METHOD_NAME)
  @Mapping(source = "isNew", target = "isNew")
  @Mapping(source = "text", target = "lexemeId", qualifiedByName = MAP_LEXEME_ID_METHOD_NAME)
  @Mapping(source = "audioURL", target = "audioURL")
  LexemeEntity learnedLexemeToLexemeEntity(LearnedLexeme learnedLexeme);

  List<LexemeEntity> learnedLexemesToLexemeEntities(List<LearnedLexeme> learnedLexemes);

  @Named(MAP_TRANSLATIONS_METHOD_NAME)
  default String mapTranslations(List<String> translations) {
    return translations != null && !translations.isEmpty() ? translations.get(0) : null;
  }

  @Named(MAP_LEXEME_ID_METHOD_NAME)
  default Long mapLexemeId(String text) {
    return UUID.nameUUIDFromBytes(text.getBytes()).getMostSignificantBits();
  }
}
