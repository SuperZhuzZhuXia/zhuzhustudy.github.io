package com.zhuzhustudy.tankgame3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

/**
 * @author 朱晨华
 *
 * 坦克大战的绘图区域
 */
//为了监听键盘事件，实现KeyListener
//为了让 panel 不停的重绘子弹，需要将 MyPanel 实现 Runnable 接口，当做一个线程使用
public class MyPanel extends JPanel implements KeyListener,Runnable {

    //定义我的坦克
    MyTank myTank = null;

    //定义敌人的坦克,放入到Vector中
    List<EnemyTank> enemyTanks = new Vector<>();
    //定义一个Vector，用于存放炸弹
    //当子弹击中坦克时，就加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;

    //定义三张图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

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
            //启动敌人坦克线程，让它动起来
            new Thread(enemyTank).start();
            //给坦克加入一颗子弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect(), 2);
            //加入enemyTank的Vector成员
            enemyTank.shots.add(shot);
            //启动 shot 对象
            new Thread(shot).start();
            //加入
            enemyTanks.add(enemyTank);
        }
        //初始化图片对象
        /*image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/picture1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/picture1.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/picture1.gif"));*/
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形，默认黑色

        //画出坦克--专门写一个封装方法
        drawTank(myTank.getX(),myTank.getY(),g,myTank.getDirect(),0);//我的坦克

        //画出myTank射出的子弹
        /*
        if (myTank.shot != null && myTank.shot.isLive == true){
            System.out.println("子弹被绘制--");
            //g.fill3DRect(myTank.shot.x,myTank.shot.y,2,2,false);
            g.draw3DRect(myTank.shot.x,myTank.shot.y,2,2,false);
        }
        */
        //将我的坦克的子弹集合 shots，遍历取出绘制
        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot = myTank.shots.get(i);
            if (shot != null && shot.isLive == true){
                System.out.println("子弹被绘制--");
                //g.fill3DRect(myTank.shot.x,myTank.shot.y,2,2,false);
                g.draw3DRect(shot.x,shot.y,2,2,false);
            }else {//如果该shot对象已经无效，就从shots集合中拿掉
                myTank.shots.remove(shot);
            }
        }

        //如果 bombs 集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前这个bomb对象的life值去画出对应的图片
            if (bomb.life > 6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }else if (bomb.life > 3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            //让这个炸弹的生命值减少
            bomb.lifeDown();
            //如果bomb life为0，就从这个bombs集合中删除
            if (bomb.life == 0){
                bombs.remove(bomb);
            }
        }

        //画出敌人的坦克,需要遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            //从集合中取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断当前坦克是否存活，是存活时才画
            if (enemyTank.isLive){
                drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);//敌人的坦克
                //画出 enemyTank 的所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot= enemyTank.shots.get(j);
                    //绘制
                    if (shot.isLive == true){
                        g.draw3DRect(shot.x,shot.y,2,2,false);
                    }else {
                        //从Vector移除子弹
                        enemyTank.shots.remove(shot);
                    }
                }
            }
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

    //在判断我的子弹是否击中敌方坦克时，就不要把我们的子弹集合中所有的子弹都取出来，和敌人的所有坦克进行判断
    /*public void hitEnemyTank(){
        //遍历我们的子弹
        for (int j = 0; j < myTank.shots.size(); j++) {
            Shot shot = myTank.shots.get(j);
            if (shot != null && shot.isLive == true){
                //遍历敌人所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(myTank.shot,enemyTank);
                }
            }
        }
    }*/

    //编写方法，判断我方子弹是否击中敌方坦克
    //什么时候进行判断呢？ 在 run 方法中
    public void hitTank(Shot s , EnemyTank enemyTank){
        //判断 s 击中坦克
        switch (enemyTank.getDirect()){
            case 0://敌人坦克向上
            case 2://敌人坦克向下
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //当子弹击中敌方坦克后，需要将enemyTank从Vector中去掉
                    enemyTanks.remove(enemyTank);
                    //创建Bomb对象，加入到bombs集合中
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1://坦克向右
            case 3://坦克向左
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //创建Bomb对象，加入到bombs集合中
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wdsa按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            //改变坦克的方向
            myTank.setDirect(0);
            //修改坦克的坐标
            if (myTank.getY() > 0){
                myTank.moveUp();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_D){
            myTank.setDirect(1);
            if (myTank.getX() + 60 < 1000) {
                myTank.moveRight();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_S){
            myTank.setDirect(2);
            if (myTank.getY() + 60 < 750) {
                myTank.moveDown();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_A) {
            myTank.setDirect(3);
            if (myTank.getX() > 0) {
                myTank.moveLeft();
            }
        }
        //如果用户按下的是 J ，就发射
        if (e.getKeyCode() == KeyEvent.VK_J){
            System.out.println("用户按下了 J 键，开始射击-- ");
            //判断坦克的子弹是否销毁，这里是发射一颗子弹的情况
            /*if (myTank.shot == null || !myTank.shot.isLive){
                myTank.shotEnemyTank();
            }*/
            //发射多颗子弹
            myTank.shotEnemyTank();

        }
        //面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每间隔100毫秒，就重绘
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //判断是否击中了敌人
            if (myTank.shot != null && myTank.shot.isLive == true){
                //遍历敌人所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(myTank.shot,enemyTank);
                }
            }
            this.repaint();
        }
    }
}
