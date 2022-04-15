package com.hjxlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: Huang JX
 * @Date: 2022/04/15
 * @Description:
 */
@RestController
@RequestMapping("/propagation")
public class PropagationController {

    @Autowired
    UserService userService;

    @GetMapping("/require")
    public void require() {
        userService.transfer_required();
    }

}
