package com.bleeding.ironbox.service.question;

import com.bleeding.ironbox.dto.QuestionResultBean;

import java.util.Map;

public interface IReplyService {
    QuestionResultBean addReply(Map<String, String> params);

    QuestionResultBean deleteReply(Map<String, String> params);

    QuestionResultBean getReplyListByAnswerId(Map<String, String> params);
}
