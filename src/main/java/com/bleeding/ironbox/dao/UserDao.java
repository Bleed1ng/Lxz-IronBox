package com.bleeding.ironbox.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    List<Map<String, Object>> getUserList();

    Map<String, Object> selectUserByUserId(@Param("userId") String userId);
}
