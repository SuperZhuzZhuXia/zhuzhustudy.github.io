package com.zhuzhustudy.tankgame;

import javax.swing.*;
import java.awt.*;

/**
 * @author 朱晨华
 */
public class TankGame01 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }

    public TankGame01(){
        mp = new MyPanel();
        this.add(mp);//把面板加进去
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
