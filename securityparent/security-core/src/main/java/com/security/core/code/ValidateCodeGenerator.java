package com.security.core.code;

import com.security.core.code.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author heartccace
 * @create 2020-05-31 11:59
 * @Description TODO
 * @Version 1.0
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
