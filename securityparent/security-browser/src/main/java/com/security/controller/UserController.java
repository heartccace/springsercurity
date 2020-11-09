package com.security.controller;

import com.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heartccace
 * @create 2020-05-29 16:03
 * @Description TODO
 * @Version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/userDetail/{name}")
    public UserDetails getDetail(@PathVariable String name) {
        return userService.findUserByUserName(name);
    }

    @GetMapping("/user/{name}")
    public UserDetails getUser(@PathVariable String name) {
        return userService.findUserByUserName(name);
    }
}
