package com.bleeding.ironbox.service.question;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bleeding.ironbox.dao.QuestionDao;
import com.bleeding.ironbox.dto.QuestionResultBean;
import com.bleeding.ironbox.service.like.ILikeService;
import com.bleeding.ironbox.utils.RedisKeyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    IQuestionService questionService;

    /**
     * 查询问题列表
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean getQuestionList(Map<String, String> params) {
//        String orderMode = params.get("orderMode");
//        System.out.println(orderMode);
//        if (orderMode.equals("hot")) {
//            updateScore();
//        }

        List<Map<String, Object>> questionList = new ArrayList<>();
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> questionListNolike = questionDao.getQuestionList(params);
        // 查询问题的点赞数
        for (Map<String, Object> question : questionListNolike) {
            String questionId = question.get("questionId").toString();
            long answerLikeCount = likeService.findEntityLikeCount(1, questionId);
            question.put("likeCount", answerLikeCount);
            questionList.add(question);
        }
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
        String questionId = UUID.randomUUID().toString().toUpperCase();
        question.put("questionId", questionId);
        Integer num = questionDao.insertQuestion(question);

        String redisKey = RedisKeyUtil.getQuestionScoreKey();
        redisTemplate.opsForSet().add(redisKey, questionId);

        if (num != null && num > 0) {
            questionResultBean.setMsg("保存成功");
        } else {
            questionResultBean.setSuccess(false);
            questionResultBean.setMsg("保存失败");
        }
        return questionResultBean;
    }

    /**
     * 根据用户ID查询问题列表
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean getQuestionListByUserId(Map<String, String> params) {
        List<Map<String, Object>> questionList = new ArrayList<>();
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);
        // 查询问题列表
        List<Map<String, Object>> questionListNolike = questionDao.getQuestionListByUserId(params);
        // 查询问题的点赞数
        for (Map<String, Object> question : questionListNolike) {
            String questionId = question.get("questionId").toString();
            long answerLikeCount = likeService.findEntityLikeCount(1, questionId);
            question.put("likeCount", answerLikeCount);
            questionList.add(question);
        }
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(questionList);

        questionResultBean.setResult(questionList);
        questionResultBean.setPageInfo(pageInfo);
        return questionResultBean;
    }

    /**
     * 根据用户ID查询问题列表
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean getFollowQuestionListByUserId(Map<String, String> params) {
        List<Map<String, Object>> questionList = new ArrayList<>();
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);
        // 查询问题列表
        List<Map<String, Object>> questionListNolike = questionDao.getFollowQuestionListByUserId(params);
        // 查询问题的点赞数
        for (Map<String, Object> question : questionListNolike) {
            String questionId = question.get("questionId").toString();
            long answerLikeCount = likeService.findEntityLikeCount(1, questionId);
            question.put("likeCount", answerLikeCount);
            questionList.add(question);
        }
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(questionList);

        questionResultBean.setResult(questionList);
        questionResultBean.setPageInfo(pageInfo);
        return questionResultBean;
    }


    /**
     * 更新问题热度
     *
     * @return
     */
    @Override
    public Integer updateScore() {
        // 查询问题ID以及回答数量
        List<Map<String, Object>> list = questionService.getAnswerCount();
        for (Map<String, Object> el : list) {
            String questionId = (String) el.get("questionId");
            long answerCount = (long) el.get("answerCount");
            // 点赞数量
            long likeCount = likeService.findEntityLikeCount(1, questionId);

            // 计算权重
            double score = answerCount + (likeCount * 2);
            // 更新问题热度
            return questionDao.updateScore(questionId, score);
        }
        return null;
    }

    /**
     * 查询问题ID以及回答数量
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getAnswerCount() {
        return questionDao.getAnswerCount();
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
        String questionId = params.get("questionId");
        answer.put("answerId", UUID.randomUUID().toString().toUpperCase());
        Integer num = questionDao.insertAnswer(answer);

        String redisKey = RedisKeyUtil.getQuestionScoreKey();
        redisTemplate.opsForSet().add(redisKey, questionId);

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

}
