package com.bleeding.ironbox.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnswerDao {
    List<Map<String, Object>> getAnswerListByUserId(Map<String, String> params);
}
