package com.zhuzhustudy.tankgame3;

import javax.swing.*;

/**
 * @author 朱晨华
 */
public class TankGame03 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame03 tankGame01 = new TankGame03();
    }

    public TankGame03(){
        mp = new MyPanel();

        //将mp放入到Thread，并启动
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp);//把面板加进去
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
