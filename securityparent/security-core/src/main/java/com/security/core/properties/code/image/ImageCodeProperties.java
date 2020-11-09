package com.security.core.properties.code.image;

import com.security.core.properties.code.sms.SmsCodeProperties;
import lombok.Data;

/**
 * @author heartccace
 * @create 2020-05-30 16:15
 * @Description 配置属性类，可以通过yml中进行值的配置
 * 提供配置化拦截功能，对生成的图片进行定制
 * @Version 1.0
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {
    private int width; //图形验证码的宽
    private int height; // 图形验证码的高

    public ImageCodeProperties() {
        super.setLength(4);
    }
}
