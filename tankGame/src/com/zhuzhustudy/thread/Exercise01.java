package com.zhuzhustudy.thread;

/**
 * @author 朱晨华
 */
public class Exercise01{
    public static void main(String[] args) throws InterruptedException {
        //创建一个Cat对象，可以当作线程使用
        Cat cat = new Cat();
        cat.start();
        for (int i = 0; i < 60; i++) {
            System.out.println("线程名" + Thread.currentThread().getName() + i);
            //让主线程休眠1s
            Thread.sleep(1000);
        }
    }
}
//1、当一个类继承了 Thread类，该类就可以当作线程使用。
//2、重写run方法，写上自己的逻辑代码
class Cat extends Thread{
    int times = 0;
    @Override
    public void run() {
        while (true) {
            System.out.println("我是一只小猫咪--" + ++times + "线程名" + Thread.currentThread().getName() );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (times == 80){
                break;
            }
        }
    }
}