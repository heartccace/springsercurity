package com.security.service;

import com.security.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author heartccace
 * @create 2020-05-21 16:58
 * @Description TODO
 * @Version 1.0
 */
public interface IUserService {
    UserDetails findUserByUserName(String name);
}
