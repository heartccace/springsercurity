package com.security.core.properties.code.sms;

import lombok.Data;

/**
 * @author heartccace
 * @create 2020-05-31 14:42
 * @Description TODO
 * @Version 1.0
 */
@Data
public class SmsCodeProperties {
    private int length = 6; // 图形验证码的长度
    private int expiredIn = 60; // 图形验证码的过期时间
    private String url; // 进行图片验证的url
}
