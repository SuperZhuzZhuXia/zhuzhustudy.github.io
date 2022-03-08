package com.zhuzhustudy.tankgame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

/**
 * @author 朱晨华
 *
 * 坦克大战的额绘图区域
 */
//为了监听键盘事件，实现KeyListener
public class MyPanel extends JPanel implements KeyListener {
    //定义我的坦克
    MyTank myTank = null;
    //定义敌人的坦克,放入到Vector中
    List<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;

    //构造器
    public MyPanel(){
        myTank = new MyTank(100,100);//初始化自己的坦克
        myTank.setSpeed(2);
        //初始化敌人的坦克
        for (int i = 0; i < enemyTankSize; i++) {
            //创建一个敌人的坦克
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            //设置方向
            enemyTank.setDirect(2);
            //加入
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形，默认黑色

        //画出坦克--专门写一个封装方法
        drawTank(myTank.getX(),myTank.getY(),g,myTank.getDirect(),0);//我的坦克
        //画出敌人的坦克,需要遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);//敌人的坦克
        }
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
            case 1://表示向右
                g.fill3DRect(x,y,60,10,false);//画出坦克左边的轮子
                g.fill3DRect(x,y+30,60,10,false);//画出坦克右边的轮子
                g.fill3DRect(x+10,y+10,40,20,false);//画出坦克中间的矩形
                g.fillOval(x+20,y+10,20,20);//中间的圆形
                g.drawLine(x+30,y+20,x+60,y+20);//这是炮筒
                break;
            case 2://表示向下
                g.fill3DRect(x,y,10,60,false);//画出坦克左边的轮子
                g.fill3DRect(x+30,y,10,60,false);//画出坦克右边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);//画出坦克中间的矩形
                g.fillOval(x+10,y+20,20,20);//中间的圆形
                g.drawLine(x+20,y+30,x+20,y+60);//这是炮筒
                break;
            case 3://表示向左
                g.fill3DRect(x,y,60,10,false);//画出坦克左边的轮子
                g.fill3DRect(x,y+30,60,10,false);//画出坦克右边的轮子
                g.fill3DRect(x+10,y+10,40,20,false);//画出坦克中间的矩形
                g.fillOval(x+20,y+10,20,20);//中间的圆形
                g.drawLine(x+30,y+20,x,y+20);//这是炮筒
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wdsa按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            //改变坦克的方向
            myTank.setDirect(0);
            //修改坦克的坐标
            myTank.moveUp();
        }else if (e.getKeyCode() == KeyEvent.VK_D){
            myTank.setDirect(1);
            myTank.moveRight();
        }else if (e.getKeyCode() == KeyEvent.VK_S){
            myTank.setDirect(2);
            myTank.moveDown();
        }else if (e.getKeyCode() == KeyEvent.VK_A){
            myTank.setDirect(3);
            myTank.moveLeft();
        }
        //面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
