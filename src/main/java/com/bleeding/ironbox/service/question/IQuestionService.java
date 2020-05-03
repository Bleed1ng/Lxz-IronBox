package com.bleeding.ironbox.service.question;

import com.bleeding.ironbox.dto.QuestionResultBean;

import java.util.List;
import java.util.Map;

public interface IQuestionService {

    QuestionResultBean getQuestionList(Map<String, String> params);

    QuestionResultBean deleteQuestion(Map<String, String> params);

    QuestionResultBean getQuestionById(Map<String, String> params);

    QuestionResultBean addQuestion(Map<String, String> params);

    QuestionResultBean addAnswer(Map<String, String> params);

    QuestionResultBean deleteAnswer(Map<String, String> params);

    QuestionResultBean getQuestionListByUserId(Map<String, String> params);

    Integer updateScore();

    List<Map<String, Object>> getAnswerCount();
}
