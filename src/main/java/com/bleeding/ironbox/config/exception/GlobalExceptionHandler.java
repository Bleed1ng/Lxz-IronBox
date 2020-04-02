package com.bleeding.ironbox.config.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object myExceptionHandler() {
        return "您访问的页面异常，请稍后重试";
    }

    @ExceptionHandler(java.lang.NullPointerException.class)
    @ResponseBody
    public ModelAndView nullPointerExceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e.toString());
        mv.setViewName("error");
        return mv;
    }

    @ExceptionHandler(java.lang.ArithmeticException.class)
    public String arithmeticExceptionHandler(Exception e) {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("error", e.toString());
//        mv.setViewName("error1.html");
        return "error";
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResponse customerException(CustomException e) {
        if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()) {
            //
        }
        return AjaxResponse.error(e);
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public AjaxResponse exception(Exception e) {
//        return AjaxResponse.error(new CustomException(CustomExceptionType.OTHER_ERROR, "未知异常"));
//    }
}
