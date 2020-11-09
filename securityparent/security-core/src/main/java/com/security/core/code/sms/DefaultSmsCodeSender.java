package com.security.core.code.sms;

/**
 * @author heartccace
 * @create 2020-05-31 14:03
 * @Description TODO
 * @Version 1.0
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobil, String code) {
        System.out.println("向手机: " + mobil + "   发送: ");
    }
}
