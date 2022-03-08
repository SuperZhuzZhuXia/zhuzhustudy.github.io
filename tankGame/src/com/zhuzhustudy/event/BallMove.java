package com.zhuzhustudy.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

/**
 * @author 朱晨华
 *
 *
 */
public class BallMove extends JFrame{//
    MyPanel mp = null;
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }
    //构造器
    public BallMove(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        //窗口JFrame对象可以监听键盘事件。
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
//
class MyPanel extends JPanel implements KeyListener {
    //为了让小球可以移动， 把他的左上角的坐标(x,y)设置变量。
    int x = 10;
    int y = 10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);//默认黑色
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println((char) e.getKeyChar()+"被按下--");
        //根据用户按下的不同键位，来处理小球移动（上下左右的键）
        //再Java中会给每一个键分配一个int类值
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {// KeyEvent.VK_DOWN就是向下的箭头对应的code
            y++;
        }else if ((e.getKeyCode() == KeyEvent.VK_UP)){
            y--;
        }else if ((e.getKeyCode() == KeyEvent.VK_RIGHT)){
            x++;
        }else if ((e.getKeyCode() == KeyEvent.VK_LEFT)){
            x--;
        }
        //更新面板，重绘
       this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}