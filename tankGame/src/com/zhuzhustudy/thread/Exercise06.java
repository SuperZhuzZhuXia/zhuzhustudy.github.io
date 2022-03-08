package com.zhuzhustudy.thread;

import java.util.Scanner;

/**
 * @author 朱晨华
 *
 * 在main方法中启动两个线程，第一个线程循环随机打印100以内的整数，直到第二个线程从键盘读取了 Q 命令。
 */
public class Exercise06 {
    public static void main(String[] args) {
        T3 t3 = new T3();
        T4 t4 = new T4(t3);//注意，t3对象传入
        Thread thread1 = new Thread(t3);
        Thread thread2 = new Thread(t4);
        thread1.start();
        thread2.start();
    }
}
class T3 implements Runnable{
    private boolean loop = true;
    @Override
    public void run() {
        while (loop){
            //输出1-100的整数
            System.out.println((int)(Math.random() * 100 + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
//直到这个线程读取到Q命令
class T4 implements Runnable{
    private T3 t3;
    private Scanner scanner = new Scanner(System.in);

    public T4(T3 t3) {//构造器，直接传入T3对象
        this.t3 = t3;
    }
    @Override
    public void run() {
        while (true) {
            //接受用户的输入
            System.out.println("请输入指令 Q 来退出--");
            char key = scanner.next().toUpperCase().charAt(0);
            if (key == 'Q'){
                //以通知的方式结束t3线程
                //去修改 loop 这个变量
                t3.setLoop(false);
                break;//退出
            }
        }
    }
}