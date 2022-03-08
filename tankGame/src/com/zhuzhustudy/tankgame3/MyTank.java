package com.zhuzhustudy.tankgame3;

import java.util.Vector;

/**
 * @author 朱晨华
 */
public class MyTank extends Tank {
    //定义一个Shot对象,表示一个射击的行为
    Shot shot = null;
    //可以发射多个子弹
    Vector<Shot> shots = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank(){

        //发射多颗子弹，但控制在面板上只能有5颗
        if (shots.size() == 5){
            return;
        }

        //创建 Shot 对象
        switch (getDirect()){
            case 0://向上
                this.shot = new Shot(getX() + 20,getY(),0,2);
                break;
            case 1://向右
                this.shot = new Shot(getX() + 60,getY() + 20,1,2);
                break;
            case 2://向下
                this.shot = new Shot(getX() + 20,getY() + 60,2,2);
                break;
            case 3://向左
                this.shot = new Shot(getX(),getY() + 20,3,2);
                break;
        }

        //把创建的shot放入到shots
        shots.add(shot);

        //启动shot线程
        new Thread(shot).start();
    }
}
