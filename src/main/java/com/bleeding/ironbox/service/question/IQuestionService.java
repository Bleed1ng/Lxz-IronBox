package com.bleeding.ironbox.service.question;

import com.bleeding.ironbox.dto.QuestionResultBean;

import java.util.Map;

public interface IQuestionService {

    QuestionResultBean getQuestionList(Map<String, String> params);
}
