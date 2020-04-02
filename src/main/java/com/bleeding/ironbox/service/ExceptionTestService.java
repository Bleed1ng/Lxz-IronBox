package com.bleeding.ironbox.service;

import com.bleeding.ironbox.config.exception.CustomException;
import com.bleeding.ironbox.config.exception.CustomExceptionType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExceptionTestService {
    // 服务层，模拟系统异常
    public void systemError() throws CustomException {
        try {
            Class.forName("com.mysql.jdbc.xxxx.Driver");
        } catch (ClassNotFoundException e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "ClassNotFoundException异常");
        }
    }

    // 服务层，模拟用户输入数据导致的校验异常
    public List<String> userError(int input) throws CustomException {
        if (input < 0) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "您输入的数据有误");
        } else {
            List<String> list = new ArrayList<String>();
            list.add("Kobe");
            list.add("LeBron");
            return list;
        }
    }
}
