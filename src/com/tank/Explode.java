package com.tank;

import java.awt.*;

/**
 * @Auther: Epiphany
 * @Date: 2022/3/18 - 03 - 18 - 9:35
 * @Description: com.tank
 * @version: 1.0
 */
public class Explode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;

    private boolean living = true;
    TankFrame tf = null;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();

    }


    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        System.out.println(step);

        if (step >= ResourceMgr.explodes.length){
            tf.listExpl.remove(this);
        }
    }
}