package com.bleeding.ironbox.controller.question;

import com.bleeding.ironbox.dto.AnswerResultBean;
import com.bleeding.ironbox.service.question.IAnswerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/answer")
@RestController
public class AnswerController {
    @Resource
    private IAnswerService answerService;

    @RequestMapping(value = "getAnswerListByUserId", method = RequestMethod.POST)
    public AnswerResultBean getAnswerListByUserId(@RequestBody Map<String, String> params) {
        return answerService.getAnswerListByUserId(params);
    }
}
