package com.security.core.code.sms;

import com.security.core.code.ValidateCode;
import com.security.core.code.ValidateCodeGenerator;
import com.security.core.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author heartccace
 * @create 2020-05-31 13:54
 * @Description TODO
 * @Version 1.0
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        int count = securityProperties.getCode().getSms().getLength();
        String code = RandomStringUtils.randomNumeric(count);
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpiredIn());
    }
}
