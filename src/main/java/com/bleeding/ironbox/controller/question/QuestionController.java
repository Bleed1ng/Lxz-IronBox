package com.bleeding.ironbox.controller.question;

import com.bleeding.ironbox.dto.QuestionResultBean;
import com.bleeding.ironbox.service.question.IQuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/question")
@RestController
public class QuestionController {
    @Resource
    private IQuestionService questionService;

    /**
     * 查询全部问题列表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "getQuestionList", method = RequestMethod.POST)
    public QuestionResultBean getQuestionList(@RequestBody Map<String, String> params) {
        return questionService.getQuestionList(params);
    }

    /**
     * 删除问题帖子
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "deleteQuestion", method = RequestMethod.GET)
    public QuestionResultBean deleteQuestion(@RequestParam Map<String, String> params) {
        return questionService.deleteQuestion(params);
    }

    /**
     * 根据ID查询单个问题详情
     *
     * @param questionId
     * @return
     */
    @RequestMapping(value="getQuestionById",method=RequestMethod.GET)
    public QuestionResultBean getQuestionById(@RequestParam("questionId") String questionId) {
        return questionService.getQuestionById(questionId);
    }
}
