package com.zhuzhustudy.tankgame;

import javax.swing.*;
import java.awt.*;

/**
 * @author 朱晨华
 *
 * 坦克大战的额绘图区域
 */
public class MyPanel extends JPanel{
    //定义我的坦克
    MyTank myTank = null;
    public MyPanel(){
        myTank = new MyTank(100,100);//初始化自己的坦克
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形，默认黑色

        //画出坦克--专门写一个封装方法
        drawTank(myTank.getX(),myTank.getY(),g,0,0);
    }
    //画坦克
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        //根据不同类型的坦克，设置颜色
        switch (type){
            case 0://我们的坦克
                g.setColor(Color.red);
                break;
            case 1://敌人的坦克
                g.setColor(Color.cyan);
                break;
        }
        //根据坦克不同的方向，来绘制坦克
        switch (direct){
            case 0://表示向上
                g.fill3DRect(x,y,10,60,false);//画出坦克左边的轮子
                g.fill3DRect(x+30,y,10,60,false);//画出坦克右边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);//画出坦克中间的矩形
                g.fillOval(x+10,y+20,20,20);//中间的圆形
                g.drawLine(x+20,y+30,x+20,y);//这是炮筒
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }
}
