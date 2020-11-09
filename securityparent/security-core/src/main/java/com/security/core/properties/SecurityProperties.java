package com.security.core.properties;

import com.security.core.properties.code.ValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author heartccace
 * @create 2020-05-29 17:23
 * @Description TODO
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "sscc.security")
@Data
public class SecurityProperties {
    BrowserProperties browserProperties = new BrowserProperties();
    ValidateCodeProperties code = new ValidateCodeProperties();
}
