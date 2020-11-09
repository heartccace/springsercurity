package com.security.core.properties.code;

import com.security.core.properties.code.image.ImageCodeProperties;
import com.security.core.properties.code.sms.SmsCodeProperties;
import lombok.Data;

/**
 * @author heartccace
 * @create 2020-05-30 16:17
 * @Description TODO
 * @Version 1.0
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
    private SmsCodeProperties sms = new SmsCodeProperties();
}
