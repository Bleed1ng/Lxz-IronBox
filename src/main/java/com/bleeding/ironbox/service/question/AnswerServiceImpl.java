package com.bleeding.ironbox.service.question;

import com.bleeding.ironbox.dao.AnswerDao;
import com.bleeding.ironbox.dto.AnswerResultBean;
import com.bleeding.ironbox.service.like.ILikeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl implements IAnswerService{
    @Resource
    private AnswerDao answerDao;
    @Resource
    AnswerResultBean answerResultBean;
    @Resource
    ILikeService likeService;

    @Override
    public AnswerResultBean getAnswerListByUserId(Map<String, String> params) {
        List<Map<String, Object>> answerList = new ArrayList<>();
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);

        // 查询回答列表
        List<Map<String, Object>> answerListNolike = answerDao.getAnswerListByUserId(params);
        // 查询回答的点赞数
        for (Map<String, Object> answer : answerListNolike) {
            String answerId = answer.get("answerId").toString();
            long answerLikeCount = likeService.findEntityLikeCount(2, answerId);
            answer.put("likeCount", answerLikeCount);
            answerList.add(answer);
        }

        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(answerList);
        answerResultBean.setResult(answerList);
        answerResultBean.setPageInfo(pageInfo);
        return answerResultBean;
    }
}
