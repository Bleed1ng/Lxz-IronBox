package com.bleeding.ironbox.controller.user;

import com.bleeding.ironbox.dto.UserResultBean;
import com.bleeding.ironbox.service.user.IUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/")
@RestController
public class LoginController {
    @Resource
    private IUserService userService;

    /**
     * 登录验证
     * @param params
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public UserResultBean login(@RequestBody Map<String, String> params) {
        return userService.login(params);
    }

    /**
     * 用户注册
     * @param params
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public UserResultBean register(@RequestBody Map<String, String> params) {
        return userService.register(params);
    }
}
