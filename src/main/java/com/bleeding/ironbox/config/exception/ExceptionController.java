package com.bleeding.ironbox.config.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExceptionController {
    @RequestMapping(value = "/toError1", method = RequestMethod.GET)
    public String toError1() {
        return "error1.html";
    }
}
