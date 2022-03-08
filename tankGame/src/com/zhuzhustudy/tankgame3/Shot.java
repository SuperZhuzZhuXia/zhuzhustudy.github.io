package com.zhuzhustudy.tankgame3;

/**
 * @author 朱晨华
 */
public class Shot implements Runnable{

    //子弹
    int x;//子弹的x坐标
    int y;//子弹的y坐标
    int direct = 0;//子弹的方向
    int speed = 2;//子弹的速度
    boolean isLive = true;//子弹是否存活

    //构造器
    public Shot(int x, int y, int direct, int speed) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向来改变x、y坐标
            switch (direct){
                case 0: //上
                    y -= speed;
                    break;
                case 1: //右
                    x += speed;
                    break;
                case 2: //下
                    y += speed;
                    break;
                case 3: //左
                    x -= speed;
                    break;
            }
            System.out.println("子弹的x坐标：" + x + "   子弹的y坐标：" + y);
            //当子弹移动到面板的边界时销毁
            //当子弹碰到敌人坦克时，也应该退出
            if (!(x >= 0 && x <=1000 && y >= 0 && y <= 750 && isLive)){
                System.out.println("子弹退出--");
                isLive = false;
                break;
            }
        }
    }
}
