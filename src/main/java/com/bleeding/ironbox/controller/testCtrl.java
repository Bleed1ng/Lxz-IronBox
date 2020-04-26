package com.bleeding.ironbox.controller;

import com.bleeding.ironbox.config.exception.AjaxResponse;
import com.bleeding.ironbox.dto.User;
import com.bleeding.ironbox.dto.UserResultBean;
import com.bleeding.ironbox.service.ExceptionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class testCtrl {
    @Autowired
    RestTemplate restTemplate;
    @Resource
    ExceptionTestService exceptionTestService;

    @RequestMapping("/test")
    public String test() {
        return "Hello! My IronBox!";
    }

    @RequestMapping("/ex/test")
    public String show() {
        String str = null;
        str.length();
        return "index";
    }

    @RequestMapping("/ex/system")
    public @ResponseBody AjaxResponse exceptionSystem() {
        exceptionTestService.systemError();
        return AjaxResponse.success();
    }

    @RequestMapping("/ex/user")
    public @ResponseBody AjaxResponse exceptionUser(Integer input) {
        return AjaxResponse.success(exceptionTestService.userError(input));
    }

    @GetMapping("/getForObject")
    public Object getForObject() {
        // 远程访问的url
        String url = "http://localhost:8085/user/getUserList";
        // 请求入参（可以为空）
        Map<String, Long> paramMap = new HashMap<String, Long>();
        UserResultBean result = restTemplate.getForObject(url, UserResultBean.class, paramMap);
        return result;
    }

    @GetMapping("/getForEntity")
    public Object getForEntity() {
        // 远程访问的url
        String url = "http://localhost:8085/user/getUserList";
        // 请求入参（可以为空）
        Map<String, Long> paramMap = new HashMap<String, Long>();
        // 使用ResponseEntity包装返回结果
        ResponseEntity<HashMap> responseEntity = restTemplate.getForEntity(url, HashMap.class, paramMap);
        // 返回状态码包装类
        HttpStatus statusCode = responseEntity.getStatusCode();
        // 返回状态码
        int statusCodeValue = responseEntity.getStatusCodeValue();
        // Http返回头
        HttpHeaders headers = responseEntity.getHeaders();
        // 返回对应的请求结果
        return responseEntity.getBody();
    }

    @GetMapping("/postForObject")
    public User postForObject() {
        // 远程访问的url
        String url = "http://localhost:8085/user/addUser2";
        // Post方法必须使用MultiValueMap传参，使用UserDTO传参也可以
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("userId", 001);
        paramMap.add("userName", "Bleeding");
        // 返回对象
        User userDTO = restTemplate.postForObject(url, paramMap, User.class);
        return userDTO;
    }

    @GetMapping("/postForObject2")
    public User postForObject2() {
        // 声明一个请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 远程访问的url
        String url = "http://localhost:8085/user/addUser3";
        /**
         * 如果或是RequestBody的形式，此处就不能再使用MultiValueMap传参，会报错；可以使用HasMap代替，但是会有警告。
         */
        // 入参使用包装类
        User userDTO = new User();
        userDTO.setUserId(003);
        userDTO.setUserName("Bleeding");
        // 再用HttpEntity包装，传入请求头和UserDTO
        HttpEntity<User> entityParam = new HttpEntity<User>(userDTO, headers);
        User result = restTemplate.postForObject(url, entityParam, User.class);
        return result;
    }

    @GetMapping("/postForEntity")
    public User postForEntity() {
        // 远程访问的url
        String url = "http://localhost:8085/user/addUser1";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("userId", 004);
        paramMap.add("userName", "Bleeding");

        ResponseEntity<User> userDTOResponseEntity = restTemplate.postForEntity(url, paramMap, User.class);
        // 返回状态码包装类
        HttpStatus statusCode = userDTOResponseEntity.getStatusCode();
        // 返回状态码
        int statusCodeValue = userDTOResponseEntity.getStatusCodeValue();
        // Http返回头
        HttpHeaders headers = userDTOResponseEntity.getHeaders();
        // 返回对应的请求结果
        return userDTOResponseEntity.getBody();
    }

    @GetMapping("/exchange")
    public User exchange() {
        // 远程访问的url
        String url = "http://localhost:8085/user/addUser1";
        // 传参使用MultiValueMap
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("userId", 005);
        paramMap.add("userName", "Bleeding exchange");
        // HttpEntity包装传参
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(paramMap);
        // ResponseEntity包装返回结果
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, User.class);
        return response.getBody();
    }
}
