package com.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Epiphany
 * @Date: 2022/3/16 - 03 - 16 - 18:11
 * @Description: com.tank
 * @version: 1.0
 */
public class TankFrame extends Frame {
    static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"), GAME_HEIGHT = PropertyMgr.getInt("gameHeight");
    Tank myTank=new Tank(200,200,Direction.d,this,Group.GOOD);
    List<Bullet> list=new ArrayList<>();
    /*Bullet bullet=new Bullet(200,200,Direction.d);*/
    List<Tank> listTank=new ArrayList<>();
    List<Explode> listExpl=new ArrayList<>();


    /*Explode explode=new Explode(10,40,this);//测试爆炸*/

    public TankFrame(){


        setResizable(false);
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setTitle("坦克大会战");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new MyKeyListener());
    }
    class MyKeyListener extends KeyAdapter {
        boolean L = false;
        boolean R = false;
        boolean U = false;
        boolean D = false;
        @Override//挤压
        public void keyPressed(KeyEvent e) {
            System.out.println("挤压");
            int keyCode = e.getKeyCode();
            switch ( keyCode ){
                case KeyEvent.VK_LEFT :
                    L=true;  break;
                case KeyEvent.VK_RIGHT :
                    R=true;  break;
                case KeyEvent.VK_UP :
                    U=true;  break;
                case KeyEvent.VK_DOWN :
                    D=true;  break;
                default: break;


            }


            setTankDir();
            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }

        private void setTankDir() {
            if( L!=true && R!=true && U!=true && D!=true ){
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
                if(L==true)
                    myTank.setDr(Direction.l);
                if(R==true)
                    myTank.setDr(Direction.r);
                if(U==true)
                    myTank.setDr(Direction.u);
                if(D==true)
                    myTank.setDr(Direction.d);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("释放");
            int keyCode = e.getKeyCode();
            switch ( keyCode ){
                case KeyEvent.VK_LEFT :
                    L=false;  break;
                case KeyEvent.VK_RIGHT :
                    R=false;  break;
                case KeyEvent.VK_UP :
                    U=false;  break;
                case KeyEvent.VK_DOWN :
                    D=false;  break;
                case KeyEvent.VK_CONTROL :
                    myTank.fire();
                default: break;


            }
            setTankDir();
        }
    }


    @Override
    public void paint(Graphics g) {




        myTank.repaint(g);

        Color c=g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量:" + list.size(), 10, 60);
        g.drawString("敌人的数量:" + listTank.size(), 10, 80);
        g.setColor(c);
        for(int i=0;i<list.size();i++){
            list.get(i).repaint(g);
        }

        for(int i=0;i<listTank.size();i++){
            listTank.get(i).repaint(g);
        }
        for(int i=0;i<listExpl.size();i++){
            listExpl.get(i).paint(g);
        }


        for (int i = 0; i<list.size(); i++) {
            for(int j=0;j<listTank.size();j++){
                list.get(i).collide(listTank.get(j));
            }
        }

    }

    //解决双闪烁
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
