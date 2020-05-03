//package com.bleeding.ironbox.utils;
//
//
//import com.bleeding.ironbox.dao.QuestionDao;
//import com.bleeding.ironbox.dto.QuestionResultBean;
//import com.bleeding.ironbox.dto.ResultBean;
//import com.bleeding.ironbox.service.like.ILikeService;
//import com.bleeding.ironbox.service.question.IQuestionService;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.BoundSetOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.Map;
//
//public class QuestionScoreRefreshJob implements Job {
//    private static final Logger logger = LoggerFactory.getLogger(QuestionScoreRefreshJob.class);
//
//    @Resource
//    private RedisTemplate redisTemplate;
//    @Resource
//    IQuestionService questionService;
//    @Resource
//    ILikeService likeService;
//    @Resource
//    RedisKeyUtil redisKeyUtil;
//
//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        String redisKey = RedisKeyUtil.getQuestionScoreKey();
//        BoundSetOperations operations = redisTemplate.boundSetOps(redisKey);
//
//        if (operations.size() == 0) {
//            logger.info("[任务暂停] 没有需要刷新的数据");
//            return;
//        }
//
//        logger.info("[任务开始] 正在刷新问题热度：" + operations.size());
//        while (operations.size() > 0) {
//            this.refresh((String) operations.pop());
//        }
//        logger.info("[任务结束] 问题热度刷新完毕");
//    }
//
//    private void refresh(String questionId) {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("questionId", questionId);
//        QuestionResultBean question = questionService.getQuestionById(params);
//        if (question == null) {
//            logger.error("该帖子不存在：id = " + questionId);
//            return;
//        }
//        // 回答数量
////        int answerCount = question.getResult(question).answerCount;
//        // 点赞数量
//        long likeCount = likeService.findEntityLikeCount(1, questionId);
//
//        // 计算权重
//        double score = likeCount * 2;
//        // 更新问题热度
//        questionService.updateScore(questionId, score);
//    }
//}
