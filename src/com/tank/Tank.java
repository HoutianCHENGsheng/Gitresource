package com.tank;

import java.awt.*;
import java.util.Random;

/**
 * @Auther: Epiphany
 * @Date: 2022/3/17 - 03 - 17 - 10:17
 * @Description: com.tank
 * @version: 1.0
 */
public class Tank {
    private int x;
    private int y;
    private Direction dr=Direction.d;
    private  final int seed=PropertyMgr.getInt("tankSpeed");
    private  boolean moving=true;
    private TankFrame tankFrame;
    private boolean live=true;
    private  Group group=Group.BAD;
    private Random random=new Random();
    Rectangle rectangle=new Rectangle();

    public static int tankwidth=ResourceMgr.tankR.getWidth();
    public static int tankheight=ResourceMgr.tankD.getHeight();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void repaint(Graphics g){
        if( !live ){
            tankFrame.listTank.remove(this);
        }
        //判断坦克类型，通过方向改变图片
        dirImage(g,this.group);

        move();

    }

    private void dirImage(Graphics g,Group group1) {
        if(group1==Group.BAD){
            switch ( dr ){
                case l:
                    g.drawImage(ResourceMgr.BadtankL,x,y,null);break;
                case r:
                    g.drawImage(ResourceMgr.BadtankR,x,y,null);break;
                case u:
                    g.drawImage(ResourceMgr.BadtankU,x,y,null);break;
                case d:
                    g.drawImage(ResourceMgr.BadtankD,x,y,null);break;
            }
        }
        if(group1==Group.GOOD){
            switch ( dr ){
                case l:
                    g.drawImage(ResourceMgr.tankL,x,y,null);break;
                case r:
                    g.drawImage(ResourceMgr.tankR,x,y,null);break;
                case u:
                    g.drawImage(ResourceMgr.tankU,x,y,null);break;
                case d:
                    g.drawImage(ResourceMgr.tankD,x,y,null);break;
            }
        }

    }

    private void move() {
        if(moving==false) return;
        switch ( dr ) {
            case d:
                y += seed;
                break;
            case l:
                x -= seed;
                break;
            case r:
                x += seed;
                break;
            case u:
                y -= seed;
                break;

            default:
                break;
        }

        rectangle.x = this.x;
        rectangle.y = this.y;

        //敌人随机开火
        if(random.nextInt(100)>90 && this.group==Group.BAD) fire();;

        //敌人随机改变方向
        if(this.group==Group.BAD && random.nextInt(100)>90){
            dr=Direction.values()[random.nextInt(4)];
        }

        //位置边界问题
        if( this.x<0 ) x=0;
        if( this.y< 30 ) y=30;
        if( this.x> tankFrame.GAME_WIDTH-tankwidth )  x=tankFrame.GAME_WIDTH-tankwidth-2;
        if( this.y> tankFrame.GAME_HEIGHT-tankheight )  y=tankFrame.GAME_HEIGHT-tankheight-2;

    }

    public Tank(int x, int y, Direction dr,TankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.dr = dr;
        this.tankFrame=tankFrame;
        this.group=group;

        rectangle.x=this.x;
        rectangle.y=this.y;
        rectangle.height=this.tankheight;
        rectangle.width=this.tankwidth;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDr() {
        return dr;
    }

    public void setDr(Direction dr) {
        this.dr = dr;
    }

    public void fire() {

        if (dr==Direction.u || dr==Direction.d){
            int bX = this.x + tankwidth/2 - Bullet.widthY/2;
            int bY = this.y + tankheight/2 - Bullet.heightY/2;
            tankFrame.list.add(new Bullet(bX, bY, this.dr, this.tankFrame,this.getGroup()));
        }
        if (dr==Direction.l || dr==Direction.r){
            int bX = this.x + tankwidth/2 - Bullet.widthX/2;
            int bY = this.y + tankheight/2 - Bullet.heightX/2;
            tankFrame.list.add(new Bullet(bX, bY, this.dr, this.tankFrame,this.getGroup()));
        }
    }





    public void die() {
        live=false;
    }
}
