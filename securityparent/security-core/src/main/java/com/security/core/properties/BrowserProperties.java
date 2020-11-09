package com.security.core.properties;

import lombok.Data;

/**
 * @author heartccace
 * @create 2020-05-29 17:23
 * @Description TODO
 * @Version 1.0
 */
@Data
public class BrowserProperties {
    // 默认的登陆界面
    private String loginPage ="/index.html";
    // 默认采用JSON的方式
    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;
}
