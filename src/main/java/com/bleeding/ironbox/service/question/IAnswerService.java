package com.bleeding.ironbox.service.question;

import com.bleeding.ironbox.dto.AnswerResultBean;

import java.util.Map;

public interface IAnswerService {
    AnswerResultBean getAnswerListByUserId(Map<String, String> params);

}
