package com.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther: Epiphany
 * @Date: 2022/3/17 - 03 - 17 - 16:48
 * @Description: com.tank
 * @version: 1.0
 */
public class ResourceMgr {
    public static BufferedImage tankL,tankR,tankU,tankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD,BadtankU,BadtankL,BadtankR,BadtankD;
    public static BufferedImage[] explodes=new BufferedImage[16];

    static{
        try {
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/goodtank.png"));
            tankL = ImageUtil.rotateImage(tankU, -90);
            tankR = ImageUtil.rotateImage(tankU, 90);
            tankD = ImageUtil.rotateImage(tankU, 180);

            BadtankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/badtank.png"));
            BadtankL = ImageUtil.rotateImage(BadtankU, -90);
            BadtankR = ImageUtil.rotateImage(BadtankU, 90);
            BadtankD = ImageUtil.rotateImage(BadtankU, 180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            for (int i = 0; i <16 ; i++) {
                explodes[i]=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
