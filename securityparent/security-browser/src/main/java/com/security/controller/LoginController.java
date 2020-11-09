package com.security.controller;

import com.security.core.properties.SecurityProperties;
import com.security.suport.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author heartccace
 * @create 2020-05-29 17:05
 * @Description TODO
 * @Version 1.0
 */
@RestController
@Slf4j
public class LoginController {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties properties;

    @RequestMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("引发处理的URL是 {%s}" + redirectUrl);
            if(StringUtils.endsWithIgnoreCase(redirectUrl,".html")) {
                redirectStrategy.sendRedirect(request,response,properties.getBrowserProperties().getLoginPage());
            }
        }

        return new SimpleResponse("访问的服务器需要身份认证，请引导用户进行认证!");
    }
}
