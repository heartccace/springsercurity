package com.security.core.code.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author heartccace
 * @create 2020-05-31 14:54
 * @Description TODO
 * @Version 1.0
 */
public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 生成验证码
     * @param request
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     * @param servletWebRequest
     */
    void validate(ServletWebRequest servletWebRequest);
}
