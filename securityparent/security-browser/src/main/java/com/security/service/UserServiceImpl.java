package com.security.service;

import com.security.entity.User;
import com.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author heartccace
 * @create 2020-05-21 16:59
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
@Service("DefaultUserDetailService")
public class UserServiceImpl implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails findUserByUserName(String name) {
        User user = userRepository.findUserByUsername(name);
        System.out.println(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return findUserByUserName(name);
    }
}
