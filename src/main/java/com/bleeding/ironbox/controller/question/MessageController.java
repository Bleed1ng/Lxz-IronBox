package com.bleeding.ironbox.controller.question;

import com.bleeding.ironbox.dto.ResultBean;
import com.bleeding.ironbox.service.message.IMessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class MessageController {
    @Resource
    private ResultBean resultBean;
    @Resource
    private IMessageService messageService;


    @RequestMapping(value = "/getAnswerMsg", method = RequestMethod.POST)
    public ResultBean getMessageByUserId (@RequestBody Map<String, String> params) {
        messageService.getMessageByUserId(params);
        return resultBean;
    }

    @RequestMapping(value = "/getReplyMsg", method = RequestMethod.POST)
    public ResultBean getReplyMsg (@RequestBody Map<String, String> params) {
        messageService.getReplyMsg(params);
        return resultBean;
    }
}
