package com.zhuzhustudy.thread;

/**
 * @author 朱晨华
 */
public class Exercise05 {
    public static void main(String[] args) throws InterruptedException {
        MyDaemonThread myDaemonThread = new MyDaemonThread();
        //如果让主线程结束，支线程自动结束，只需要将子线程设置为守护线程即可
        myDaemonThread.setDaemon(true);
        myDaemonThread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("宝强辛苦的工作...");
            Thread.sleep(1000);
        }
    }
}
class MyDaemonThread extends Thread{
    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("聊天...");
        }
    }
}