package com.bleeding.ironbox.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageDao {
    List<Map<String, Object>> getAnswerMsg(@Param("userId") String userId);

    List<Map<String, Object>> getReplyMsg(@Param("userId") String userId);
}
