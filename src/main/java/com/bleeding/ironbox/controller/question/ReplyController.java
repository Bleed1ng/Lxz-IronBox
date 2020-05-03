package com.bleeding.ironbox.controller.question;

import com.bleeding.ironbox.dto.QuestionResultBean;
import com.bleeding.ironbox.service.question.IReplyService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/reply")
@RestController
public class ReplyController {
    @Resource
    private IReplyService replyService;

    /**
     * 根据回答ID查询所有评论
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "getReplyListByAnswerId", method = RequestMethod.POST)
    public QuestionResultBean getReplyListByAnswerId(@RequestBody Map<String, String> params) {
        return replyService.getReplyListByAnswerId(params);
    }

    /**
     * 新增评论
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "addReply", method = RequestMethod.POST)
    public QuestionResultBean addReply(@RequestBody Map<String, String> params) {
        return replyService.addReply(params);
    }

    /**
     * 删除评论
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "deleteReply", method = RequestMethod.GET)
    public QuestionResultBean deleteReply(@RequestBody Map<String, String> params) {
        return replyService.deleteReply(params);
    }
}
