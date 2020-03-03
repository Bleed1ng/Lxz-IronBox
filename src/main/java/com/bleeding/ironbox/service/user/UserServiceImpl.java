package com.bleeding.ironbox.service.user;

import com.bleeding.ironbox.dao.UserDao;
import com.bleeding.ironbox.dto.UserListResultBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserListResultBean userListResultBean;
    @Resource
    private UserDao userDao;

    @Override
    public UserListResultBean getUserList() {
        List<Map<String, Object>> userList = userDao.getUserList();
        userListResultBean.setResult(userList);
        return userListResultBean;
    }
}
