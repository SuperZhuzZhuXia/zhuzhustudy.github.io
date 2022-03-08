package com.zhuzhustudy.tankgame3;

import java.util.Vector;

/**
 * @author 朱晨华
 *
 * 敌人的坦克
 */
public class EnemyTank extends Tank implements Runnable{

    //在敌人坦克类中，使用 Vector 来保存多个Shot
    Vector<Shot> shots = new Vector<>();

    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true){

            //这里我们判断，如果shots.size为0，创建一颗子弹，放入到shots集合，并启动
            if (isLive && shots.size() < 3){
                Shot s = null;
                //判断坦克的方向，创建对应的子弹
                switch (getDirect()){
                    case 0:
                        s = new Shot(getX() + 20,getY(),0,2);
                        break;
                    case 1:
                        s = new Shot(getX() + 60,getY() + 20,1,2);
                        break;
                    case 2:
                        s = new Shot(getX() + 20,getY() + 60,0,2);
                        break;
                    case 3:
                        s = new Shot(getX(),getY() + 20,0,2);
                        break;
                }
                shots.add(s);
                //启动
                new Thread(s).start();
            }

            //根据坦克的方向继续移动
            switch (getDirect()){
                case 0://上
                    //让坦克保持一个方向，走30下
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1://右
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2://下
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3://左
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            //然后随机改变坦克方向
            setDirect((int)(Math.random() * 4));
            if (isLive == false){
                break;//退出线程
            }
        }
    }
}
