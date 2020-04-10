package com.bleeding.ironbox.service.question;

import com.bleeding.ironbox.dao.QuestionDao;
import com.bleeding.ironbox.dto.QuestionResultBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements IQuestionService{
    @Resource
    private QuestionResultBean questionResultBean;
    @Resource
    private QuestionDao questionDao;

    /**
     * 查询问题列表
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean getQuestionList(Map<String, String> params) {
        List<Map<String, Object>> questionList = questionDao.getQuestionList(params);
        questionResultBean.setResult(questionList);
        return questionResultBean;
    }
}
