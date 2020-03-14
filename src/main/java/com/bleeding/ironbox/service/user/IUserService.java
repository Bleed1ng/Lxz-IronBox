package com.bleeding.ironbox.service.user;

import com.bleeding.ironbox.dto.UserResultBean;

import java.util.Map;

public interface IUserService {

    UserResultBean getUserList();

    UserResultBean login(Map<String, String> params);
}
