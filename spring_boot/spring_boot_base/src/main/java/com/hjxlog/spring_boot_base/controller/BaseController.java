package com.hjxlog.spring_boot_base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Huang JX
 * @Date: 2022/04/15
 * @Description:
 */
@RestController
@RequestMapping("/base")
public class BaseController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }

}
