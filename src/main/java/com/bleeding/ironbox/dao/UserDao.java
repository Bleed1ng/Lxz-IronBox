package com.bleeding.ironbox.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    List<Map<String, Object>> getUserList(Map<String, String> params);

    Map<String, Object> selectUserByUserId(@Param("userId") String userId);

    Integer insertUser(Map<String, String> user);

    Integer updateUser(Map<String, String> user);

    Map<String, Object> getUserById(@Param("userId") String userId);

    Integer deleteUser(@Param("ids") String ids);
}
