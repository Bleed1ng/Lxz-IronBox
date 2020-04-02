package com.bleeding.ironbox.service.user;

import com.bleeding.ironbox.dao.UserDao;
import com.bleeding.ironbox.dto.UserResultBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserResultBean userResultBean;
    @Resource
    private UserDao userDao;

    @Override
    public UserResultBean getUserList() {
        List<Map<String, Object>> userList = userDao.getUserList();
        userResultBean.setResult(userList);
        return userResultBean;
    }

    /**
     * 登录验证
     * @param params
     * @return
     */
    @Override
    public UserResultBean login(Map<String, String> params) {
        String userId = params.get("userId");
        String password = params.get("password");
        // 参数校验
        if (StringUtils.isEmpty(userId)) {
            userResultBean.setSuccess(false);
            userResultBean.setMsg("用户名[account]不能为空！");
            return userResultBean;
        }
        if (StringUtils.isEmpty(password)) {
            userResultBean.setSuccess(false);
            userResultBean.setMsg("密码[password]不能为空！");
            return userResultBean;
        }

        // 根据用户名查询数据库用户记录
        Map<String, Object> user = userDao.selectUserByUserId(userId);

        // 判断是否存在用户
        if (user == null) {
            userResultBean.setSuccess(false);
            userResultBean.setMsg("用户名或密码不正确！");
            return userResultBean;
        }
        String pwd = user.get("password") == null ? null : user.get("password").toString();

        // 比较密码正确与否
        if (password.equals(pwd)) {
            userResultBean.setSuccess(true);
            userResultBean.setMsg("登录成功！");
            userResultBean.setResult(user);
        } else {
            userResultBean.setSuccess(false);
            userResultBean.setMsg("用户名或密码不正确！");
        }
        return userResultBean;
    }

    @Override
    public UserResultBean register(Map<String, String> params) {
        return null;
    }
}
