package com.bleeding.ironbox.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyDao {
    Integer insertReply(Map<String, String> reply);

    Integer deleteReply(@Param("replyId") String replyId);

    List<Map<String, Object>> getReplyListByAnswerId(Map<String, String> params);
}
