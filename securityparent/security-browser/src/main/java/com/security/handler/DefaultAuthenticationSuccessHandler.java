package com.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.core.properties.LoginType;
import com.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author heartccace
 * @create 2020-05-29 17:42
 * @Description 登陆成功根据配置返回页面或者JSON
 * 通过实现接口AuthenticationSuccessHandler或者继承SimpleUrlAuthenticationSuccessHandler
 * @Version 1.0
 */
@Component("DefaultAuthenticationSuccessHandler")
@Slf4j
public class DefaultAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        if(LoginType.JSON.equals(securityProperties.getBrowserProperties().getLoginType())) {
            response.setContentType("application/json;charset=utf-8");

            PrintWriter writer = response.getWriter();

            writer.write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request,response,authentication);
        }


    }
}
