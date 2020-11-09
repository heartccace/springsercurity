package com.security.core.properties;

/**
 * @author heartccace
 * @create 2020-05-29 18:00
 * @Description 登陆失败方式，
 * JSON：返回JSON字符串
 * REDIRECT重定向到登陆页面
 * 可以通过yml配置的方式进行配置
 * @Version 1.0
 */
public enum LoginType {
    JSON,
    REDIRECT
}
