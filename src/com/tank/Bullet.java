package com.tank;

import java.awt.*;

/**
 * @Auther: Epiphany
 * @Date: 2022/3/17 - 03 - 17 - 11:16
 * @Description: com.tank
 * @version: 1.0
 */
public class Bullet {
    private int x;
    private int y;
    private Direction dr=Direction.d;
    private  final int seed=PropertyMgr.getInt("bulletSpeed");
    private  boolean live=true;
    private TankFrame tankFrame=null;
    private  Group group=Group.BAD;
    Rectangle rectangle=new Rectangle();

    public static int widthX=ResourceMgr.bulletL.getWidth();
    public static int widthY=ResourceMgr.bulletD.getWidth();
    public static int heightX=ResourceMgr.bulletL.getHeight();
    public static int heightY=ResourceMgr.bulletD.getHeight();

    private static int width;
    private static int height;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Bullet.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Bullet.height = height;
    }

    public void repaint(Graphics g){

        if(!live){
            tankFrame.list.remove(this);
        }
        switch ( dr ){
            case l:
                g.drawImage(ResourceMgr.bulletL,x,y,null);break;
            case r:
                g.drawImage(ResourceMgr.bulletR,x,y,null);break;
            case u:
                g.drawImage(ResourceMgr.bulletU,x,y,null);break;
            case d:
                g.drawImage(ResourceMgr.bulletD,x,y,null);break;
        }
        move();



    }

    public Bullet(int x, int y, Direction dr,TankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.dr = dr;
        this.tankFrame=tankFrame;
        this.group=group;
        if(dr==Direction.l || dr==Direction.r){
            width=widthX;
            height=heightX;
        }
        if(dr==Direction.u || dr==Direction.d){
            width=widthY;
            height=heightY;
        }

        rectangle.x=this.x;
        rectangle.y=this.y;
        rectangle.height=this.height;
        rectangle.width=this.width;
    }

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void move() {

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
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;

        rectangle.x = this.x;
        rectangle.y = this.y;
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

    public int getSeed() {
        return seed;
    }

    public void collide(Tank tank) {
        if(this.group==tank.getGroup()){
            return;
        }


        if(rectangle.intersects( tank.rectangle )){
            tank.die();
            this.die();
            int X1,Y1;
            X1=tank.getX()+Tank.tankwidth/2-Explode.WIDTH/2;
            Y1=tank.getY()+Tank.tankheight/2-Explode.HEIGHT/2;
            tankFrame.listExpl.add(new Explode(X1,Y1,tankFrame));
        }

    }

    private void die() {
        live=false;
    }
}
