package com.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.core.properties.LoginType;
import com.security.core.properties.SecurityProperties;
import com.security.suport.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author heartccace
 * @create 2020-05-29 17:42
 * @Description 登陆失败根据配置返回页面或者JSON
 * 可以通过AuthenticationFailureHandler接口或者SimpleUrlAuthenticationFailureHandler实现
 * @Version 1.0
 */
@Component("DefaultAuthenticationFailureHandler")
@Slf4j
public class DefaultAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");
        if(LoginType.JSON.equals(securityProperties.getBrowserProperties().getLoginType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
        } else {
            // 父类默认使用页面
            super.onAuthenticationFailure(request,response,e);
        }

    }
}
