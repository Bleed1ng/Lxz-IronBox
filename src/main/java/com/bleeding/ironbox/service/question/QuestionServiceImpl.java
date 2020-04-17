package com.bleeding.ironbox.service.question;

import com.bleeding.ironbox.dao.QuestionDao;
import com.bleeding.ironbox.dto.QuestionResultBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements IQuestionService {
    @Resource
    private QuestionResultBean questionResultBean;
    @Resource
    private QuestionDao questionDao;

    /**
     * 查询问题列表
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean getQuestionList(Map<String, String> params) {
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> questionList = questionDao.getQuestionList(params);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(questionList);
        questionResultBean.setResult(questionList);
        questionResultBean.setPageInfo(pageInfo);
        return questionResultBean;
    }

    /**
     * 删除问题帖子
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean deleteQuestion(Map<String, String> params) {
        String ids = params.get("ids");
        Integer num = questionDao.deleteQuestion(ids);
        QuestionResultBean result = new QuestionResultBean();
        if (num != null && num > 0) {
            result.setMsg("删除成功");
        } else {
            result.setSuccess(false);
            result.setMsg("删除失败");
        }
        return result;
    }

    /**
     * 根据ID查询单个问题详情
     *
     * @param questionId
     * @return
     */
    @Override
    public QuestionResultBean getQuestionById(String questionId) {
        QuestionResultBean result = new QuestionResultBean();
        // 查询问题详情
        Map<String, Object> question = questionDao.getQuestionById(questionId);
        // 查询该问题的回答评论
        List<Map<String, Object>> replyList = questionDao.getReplyById(questionId);
        question.put("replyList", replyList);

        result.setResult(question);
        return result;
    }
}
