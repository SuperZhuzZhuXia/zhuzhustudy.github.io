package com.zhuzhustudy.thread;

/**
 * @author 朱晨华
 */
public class Homework01 {
    public static void main(String[] args) {
        T t = new T();
        new Thread(t).start();
        new Thread(t).start();
    }
}
//涉及多个线程共享资源，使用实现Runnable接口的方式
class T implements Runnable{
    private static int money = 10000;
    private boolean loop = true;
    @Override
    public void run() {
        while (loop){
            synchronized (this) {
                if (money <= 1000) {
                    System.out.println("余额不足--");
                    break;
                }
                money -= 1000;
                System.out.println(Thread.currentThread().getName() + "取出1000元，当前余额为：" + money);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}