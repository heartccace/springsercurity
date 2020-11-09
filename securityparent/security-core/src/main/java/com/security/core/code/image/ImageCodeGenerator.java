package com.security.core.code.image;

import com.security.core.code.ImageCode;
import com.security.core.code.ValidateCodeGenerator;
import com.security.core.properties.SecurityProperties;
import com.security.core.utils.ImageCodeUtil;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author heartccace
 * @create 2020-05-31 12:02
 * @Description TODO
 * @Version 1.0
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;
    @Override
    public ImageCode generate(ServletWebRequest request) {
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width", securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height", securityProperties.getCode().getImage().getHeight());
        int length = securityProperties.getCode().getImage().getLength();
        int expiredIn = securityProperties.getCode().getImage().getExpiredIn();
        return createImageCode(width,height,length,expiredIn);
    }

    private ImageCode createImageCode(int width,int height,int length, int expiredIn) {
        return ImageCodeUtil.getImageCode(width,height,length,expiredIn);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
