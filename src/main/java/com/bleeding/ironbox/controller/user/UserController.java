package com.bleeding.ironbox.controller.user;

import com.bleeding.ironbox.dto.UserListResultBean;
import com.bleeding.ironbox.service.user.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserListResultBean userListResultBean;
    @Resource
    private IUserService userService;

    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public UserListResultBean getUserList(){
        return userService.getUserList();
    }
}
