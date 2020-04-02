package com.bleeding.ironbox.controller.user;

import com.bleeding.ironbox.dto.UserDTO;
import com.bleeding.ironbox.dto.UserResultBean;
import com.bleeding.ironbox.service.user.IUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/user")
@RestController
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping(value = "getUserList", method = RequestMethod.POST)
    public UserResultBean getUserList() {
        return userService.getUserList();
    }

    @RequestMapping(value = "/showUsers", method = RequestMethod.GET)
    public String showUsers() {
        String str = null;
        str.length();
        return "error";
    }

    @RequestMapping(value = "/showUsers1", method = RequestMethod.GET)
    public String showUsers1() {
        int a = 10/0;
        return "error1.html";
    }

    @RequestMapping(value = "/addUser1")
    public UserDTO addUser1(UserDTO userDTO) {
        return userDTO;
    }

    @RequestMapping(value = "/addUser2", method = RequestMethod.POST)
    public UserDTO addUser2(int userId, String userName) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setUserName(userName);
        return userDTO;
    }

    @RequestMapping(value = "/addUser3")
    public UserDTO addUser3(@RequestBody UserDTO userDTO) {
        userDTO.setUserName(userDTO.getUserName() + "from RequestBody");
        return userDTO;
    }
}
