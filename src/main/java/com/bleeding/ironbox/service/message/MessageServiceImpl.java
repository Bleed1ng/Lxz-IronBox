package com.bleeding.ironbox.service.message;

import com.bleeding.ironbox.dao.MessageDao;
import com.bleeding.ironbox.dto.ResultBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements IMessageService {
    @Resource
    private MessageDao messageDao;
    @Resource
    private ResultBean resultBean;

    @Override
    public ResultBean getMessageByUserId(Map<String, String> params) {
        String userId = params.get("userId");
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);

        List<Map<String, Object>> answerMsgList = messageDao.getAnswerMsg(userId);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(answerMsgList);
        resultBean.setPageInfo(pageInfo);
        resultBean.setResult(answerMsgList);
        return resultBean;
    }

    @Override
    public ResultBean getReplyMsg(Map<String, String> params) {
        String userId = params.get("userId");
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);

        List<Map<String, Object>> replyMsgList = messageDao.getReplyMsg(userId);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(replyMsgList);
        resultBean.setPageInfo(pageInfo);
        resultBean.setResult(replyMsgList);
        return resultBean;
    }
}
