package com.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

/**
 * @Auther: Epiphany
 * @Date: 2022/3/16 - 03 - 16 - 17:50
 * @Description: com.tank
 * @version: 1.0
 */
public class test {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf=new TankFrame();

        int badTankCount =PropertyMgr.getInt("initTankCount");
        for (int i = 0; i < badTankCount ; i++) {
            tf.listTank.add(new Tank(50+i*30,100,Direction.d,tf,Group.BAD));
        }

        //敌人自动变化
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
