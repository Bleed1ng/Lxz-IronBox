package com.bleeding.ironbox.controller.user;

import com.bleeding.ironbox.dto.UserDTO;
import com.bleeding.ironbox.dto.UserResultBean;
import com.bleeding.ironbox.service.user.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {
    @Resource
    private IUserService userService;

    /**
     * 查询用户列表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "getUserList", method = RequestMethod.POST)
    public UserResultBean getUserList(@RequestBody Map<String, String> params) {
        return userService.getUserList(params);
    }

    /**
     * 保存用户
     * @param params
     * @return
     */
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public UserResultBean saveUser(@RequestBody Map<String, String> params) {
        return userService.saveUser(params);
    }

    /**
     * 根据id查询单个用户
     * @param userId
     * @return
     */
    @RequestMapping(value="getUserById",method=RequestMethod.GET)
    public UserResultBean getUserById(@RequestParam("userId") String userId) {
        return userService.getUserById(userId);
    }

    /**
     * 删除用户
     * @param params
     * @return
     */
    @RequestMapping(value="deleteUser",method=RequestMethod.GET)
    public UserResultBean delUser(@RequestParam Map<String,String> params) {
        return userService.deleteUser(params);
    }

    @RequestMapping(value = "/showUsers", method = RequestMethod.GET)
    public String showUsers() {
        String str = null;
        str.length();
        return "error";
    }

    @RequestMapping(value = "/showUsers1", method = RequestMethod.GET)
    public String showUsers1() {
        int a = 10 / 0;
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
