package com.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author heartccace
 * @create 2020-05-29 11:07
 * @Description TODO
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityWebConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // 允许使用HttpServletRequest限制访问
                .anyRequest()
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
