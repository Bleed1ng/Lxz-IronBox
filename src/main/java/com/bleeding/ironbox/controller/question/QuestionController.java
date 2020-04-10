package com.bleeding.ironbox.controller.question;

import com.bleeding.ironbox.dto.QuestionResultBean;
import com.bleeding.ironbox.service.question.IQuestionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public QuestionResultBean getUserList(@RequestBody Map<String, String> params) {
        return questionService.getQuestionList(params);
    }
}
// https://blog.csdn.net/qq_26094481/article/details/83659336
