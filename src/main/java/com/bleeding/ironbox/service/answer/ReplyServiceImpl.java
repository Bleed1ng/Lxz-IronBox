package com.bleeding.ironbox.service.answer;

import com.bleeding.ironbox.dao.ReplyDao;
import com.bleeding.ironbox.dto.QuestionResultBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ReplyServiceImpl implements IReplyService {
    @Resource
    private QuestionResultBean questionResultBean;
    @Resource
    private ReplyDao replyDao;

    @Override
    public QuestionResultBean getReplyListByAnswerId(Map<String, String> params) {
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);

        List<Map<String, Object>> replyList = replyDao.getReplyListByAnswerId(params);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(replyList);
        questionResultBean.setResult(replyList);
        questionResultBean.setPageInfo(pageInfo);
        return questionResultBean;
    }

    /**
     * 新增评论
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean addReply(Map<String, String> params) {
        Map<String, String> reply = params;
        reply.put("replyId", UUID.randomUUID().toString().toUpperCase());
        Integer num = replyDao.insertReply(reply);
        if (num != null && num > 0) {
            questionResultBean.setMsg("保存成功");
        } else {
            questionResultBean.setSuccess(false);
            questionResultBean.setMsg("保存失败");
        }
        return questionResultBean;
    }

    /**
     * 删除评论
     *
     * @param params
     * @return
     */
    @Override
    public QuestionResultBean deleteReply(Map<String, String> params) {
        String replyId = params.get("replyId");
        Integer num = replyDao.deleteReply(replyId);
        if (num != null && num > 0) {
            questionResultBean.setMsg("删除成功");
        } else {
            questionResultBean.setSuccess(false);
            questionResultBean.setMsg("删除失败");
        }
        return questionResultBean;
    }

}
