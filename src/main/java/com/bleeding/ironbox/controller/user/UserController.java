package com.bleeding.ironbox.controller.user;

import com.bleeding.ironbox.dto.UserResultBean;
import com.bleeding.ironbox.service.user.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/user")
@RestController
public class UserController {
    @Resource
    private UserResultBean userResultBean;
    @Resource
    private IUserService userService;

    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public UserResultBean getUserList(){
        return userService.getUserList();
    }
}