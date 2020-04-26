package com.bleeding.ironbox.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionDao {
    List<Map<String, Object>> getQuestionList(Map<String, String> params);

    Integer deleteQuestion(@Param("ids") String ids);

    Map<String, Object> getQuestionById(@Param("questionId") String questionId);

    List<Map<String, Object>> getAnswerById(Map<String, String> params);

    Integer insertQuestion(Map<String, String> params);

    Integer insertAnswer(Map<String, String> answer);

    Integer insertAnswerLike(Map<String, String> params);

    Integer deleteAnswerLike(Map<String, String> params);

    Integer deleteAnswer(String answerId);
}
