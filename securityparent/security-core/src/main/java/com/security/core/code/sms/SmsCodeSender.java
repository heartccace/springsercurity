package com.security.core.code.sms;

/**
 * @author heartccace
 * @create 2020-05-31 14:01
 * @Description TODO
 * @Version 1.0
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
