package com.bleeding.ironbox.service.message;

import com.bleeding.ironbox.dto.ResultBean;

import java.util.Map;

public interface IMessageService {
    ResultBean getMessageByUserId(Map<String, String> params);

    ResultBean getReplyMsg(Map<String, String> params);
}
