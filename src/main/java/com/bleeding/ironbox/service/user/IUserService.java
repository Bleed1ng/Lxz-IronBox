package com.bleeding.ironbox.service.user;

import com.bleeding.ironbox.dto.UserResultBean;

import java.util.Map;

public interface IUserService {

    UserResultBean adminLogin(Map<String, String> params);

    UserResultBean login(Map<String, String> params);

    UserResultBean register(Map<String, String> params);

    UserResultBean getUserList(Map<String, String> params);

    UserResultBean saveUser(Map<String, String> params);

    UserResultBean getUserById(String userId);

    UserResultBean deleteUser(Map<String, String> params);

}
