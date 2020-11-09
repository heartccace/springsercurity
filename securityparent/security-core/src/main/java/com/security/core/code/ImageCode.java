package com.security.core.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author heartccace
 * @create 2020-05-30 13:40
 * @Description TODO
 * @Version 1.0
 */
public class ImageCode extends ValidateCode{
    private BufferedImage image; // 生成图片

    /**
     *
     * @param image
     * @param code
     * @param expireIn  过多久过期（单位：s）
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
    }
    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;

    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
