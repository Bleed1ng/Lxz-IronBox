package com.bleeding.ironbox.service.question;

import com.bleeding.ironbox.dao.QuestionDao;
import com.bleeding.ironbox.dto.QuestionResultBean;
import com.bleeding.ironbox.service.like.ILikeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements IQuestionService {
    @Resource
    private QuestionResultBean questionResultBean;
    @Resource
    private QuestionDao questionDao;
    @Resource
    ILikeService likeService;

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
        if (num != null && num > 0) {
            questionResultBean.setMsg("删除成功");
        } else {
            questionResultBean.setSuccess(false);
            questionResultBean.setMsg("删除失败");
        }
        return questionResultBean;
    }

    /**
     * 根据ID查询单个问题详情
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean getQuestionById(Map<String, String> params) {
        String questionId = params.get("questionId");
        String userId = params.get("userId");

        // 查询问题详情
        Map<String, Object> question = questionDao.getQuestionById(questionId);

        // 查询问题的点赞数
        long likeCount = likeService.findEntityLikeCount(1, questionId);
        int likeStatus = likeService.findEntityLikeStatus(userId, 1, questionId);
        question.put("likeCount", likeCount);
        question.put("likeStatus", likeStatus);

        // 查询该问题的回答列表
        List<Map<String, Object>> answerList = new ArrayList<Map<String, Object>>();
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> answerListNolike = questionDao.getAnswerById(params);
        // 查询回答的点赞数
        for (Map<String, Object> answer : answerListNolike) {
            String answerId = answer.get("answerId").toString();
            long answerLikeCount = likeService.findEntityLikeCount(2, answerId);
            int answerLikeStatus = likeService.findEntityLikeStatus(userId, 2, answerId);
            answer.put("answerLikeCount", answerLikeCount);
            answer.put("answerLikeStatus", answerLikeStatus);
            answerList.add(answer);
        }
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(answerList);
        question.put("answerList", answerList);

        questionResultBean.setPageInfo(pageInfo);
        questionResultBean.setResult(question);
        return questionResultBean;
    }

    /**
     * 新增问题
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean addQuestion(Map<String, String> params) {
        Map<String, String> question = params;
        question.put("questionId", UUID.randomUUID().toString().toUpperCase());
        Integer num = questionDao.insertQuestion(question);
        if (num != null && num > 0) {
            questionResultBean.setMsg("保存成功");
        } else {
            questionResultBean.setSuccess(false);
            questionResultBean.setMsg("保存失败");
        }
        return questionResultBean;
    }

    /**
     * 新增问题回答
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean addAnswer(Map<String, String> params) {
        Map<String, String> answer = params;
        answer.put("answerId", UUID.randomUUID().toString().toUpperCase());
        Integer num = questionDao.insertAnswer(answer);
        if (num != null && num > 0) {
            questionResultBean.setMsg("保存成功");
        } else {
            questionResultBean.setSuccess(false);
            questionResultBean.setMsg("保存失败");
        }
        return questionResultBean;
    }

    /**
     * 删除回答
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean deleteAnswer(Map<String, String> params) {
        String answerId = params.get("answerId");
        Integer num = questionDao.deleteAnswer(answerId);
        if (num != null && num > 0) {
            questionResultBean.setMsg("删除成功");
        } else {
            questionResultBean.setSuccess(false);
            questionResultBean.setMsg("删除失败");
        }
        return questionResultBean;
    }

    /**
     * 回答点赞
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean answerLike(Map<String, String> params) {
        Integer num = questionDao.insertAnswerLike(params);
        if (num != null && num > 0) {
            questionResultBean.setMsg("保存成功");
        } else {
            questionResultBean.setSuccess(false);
            questionResultBean.setMsg("保存失败");
        }
        return questionResultBean;
    }

    /**
     * 取消点赞
     */
    @Override
    public QuestionResultBean answerUnLike(Map<String, String> params) {
        Integer num = questionDao.deleteAnswerLike(params);
        if (num != null && num > 0) {
            questionResultBean.setMsg("取消成功");
        } else {
            questionResultBean.setSuccess(false);
            questionResultBean.setMsg("取消失败");
        }
        return questionResultBean;
    }
}
