package com.bleeding.ironbox.service.user;

import com.bleeding.ironbox.dao.UserDao;
import com.bleeding.ironbox.dto.UserResultBean;
import com.bleeding.ironbox.utils.IronboxUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserResultBean userResultBean;
    @Resource
    private UserDao userDao;

    /**
     * 用户列表查询
     *
     * @return
     */
    @Override
    public UserResultBean getUserList(Map<String, String> params) {
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);

        List<Map<String, Object>> userList = userDao.getUserList(params);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(userList);
        userResultBean.setResult(userList);
        userResultBean.setPageInfo(pageInfo);
        return userResultBean;
    }

    /**
     * 保存用户
     *
     * @param params
     * @return
     */
    @Override
    public UserResultBean saveUser(Map<String, String> params) {
        UserResultBean result = new UserResultBean();
        Map<String, String> user = params;
        String userId = user.get("userId");
        Integer num = new Integer(0);

        // md5加密
        String password = IronboxUtil.md5(params.get("password"));
        params.put("password", password);

        if (StringUtils.isEmpty(userId)) {
            // 注册新增用户
            user.put("userId", UUID.randomUUID().toString().toUpperCase());
            num = userDao.insertUser(user);
        } else {
            // 更新用户
            num = userDao.updateUser(user);
        }

        if (num != null && num > 0) {
            result.setMsg("保存成功");
        } else {
            result.setSuccess(false);
            result.setMsg("保存失败");
        }
        return result;
    }

    /**
     * 根据ID查询单个用户
     *
     * @param userId
     * @return
     */
    @Override
    public UserResultBean getUserById(String userId) {
        UserResultBean result = new UserResultBean();
        Map<String, Object> user = userDao.getUserById(userId);
        result.setResult(user);
        return result;
    }

    /**
     * 删除用户
     *
     * @param params
     * @return
     */
    @Override
    public UserResultBean deleteUser(Map<String, String> params) {
        String ids = params.get("ids");
        Integer num = userDao.deleteUser(ids);
        UserResultBean result = new UserResultBean();
        if (num != null && num > 0) {
            result.setMsg("删除成功");
        } else {
            result.setSuccess(false);
            result.setMsg("删除失败");
        }
        return result;
    }

    /**
     * 用户登录验证
     *
     * @param params
     * @return
     */
    @Override
    public UserResultBean login(Map<String, String> params) {
        String userEmail = params.get("userEmail");
        String password = IronboxUtil.md5(params.get("password"));
        // 参数校验
        if (StringUtils.isEmpty(userEmail)) {
            userResultBean.setSuccess(false);
            userResultBean.setMsg("账号不能为空！");
            return userResultBean;
        }
        if (StringUtils.isEmpty(password)) {
            userResultBean.setSuccess(false);
            userResultBean.setMsg("密码不能为空！");
            return userResultBean;
        }

        // 根据用户名查询数据库用户记录
        // Map<String, Object> user = userDao.selectUserByUserId(userEmail);
        // 根据用户名查询数据库用户记录
        Map<String, Object> user = userDao.selectUserByUserEmail(userEmail);

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

    /**
     * 用户注册
     */
    @Override
    public UserResultBean register(Map<String, String> params) {
        return null;
    }

    /**
     * 管理员登录验证
     *
     * @param params
     * @return
     */
    @Override
    public UserResultBean adminLogin(Map<String, String> params) {
        String adminId = params.get("adminId");
        String password = params.get("password");
        // 参数校验
        if (StringUtils.isEmpty(adminId)) {
            userResultBean.setSuccess(false);
            userResultBean.setMsg("管理员ID不能为空！");
            return userResultBean;
        }
        if (StringUtils.isEmpty(password)) {
            userResultBean.setSuccess(false);
            userResultBean.setMsg("密码不能为空！");
            return userResultBean;
        }

        // 根据用户名查询数据库用户记录
        Map<String, Object> admin = userDao.selectAdminById(adminId);

        // 判断是否存在用户
        if (admin == null) {
            userResultBean.setSuccess(false);
            userResultBean.setMsg("用户名或密码不正确！");
            return userResultBean;
        }
        String pwd = admin.get("adminPassword") == null ? null : admin.get("adminPassword").toString();

        // 比较密码正确与否
        if (password.equals(pwd)) {
            userResultBean.setSuccess(true);
            userResultBean.setMsg("登录成功！");
            userResultBean.setResult(admin);
        } else {
            userResultBean.setSuccess(false);
            userResultBean.setMsg("用户名或密码不正确！");
        }
        return userResultBean;
    }
}
