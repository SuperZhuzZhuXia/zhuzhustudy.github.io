package com.zhuzhustudy.thread;

/**
 * @author 朱晨华
 */
public class Exercise04 {
    public static void main(String[] args) {
        //测试
        SellTicket01 sellTicket01 = new SellTicket01();

        Thread thread1 = new Thread(sellTicket01);
        Thread thread2 = new Thread(sellTicket01);
        Thread thread3 = new Thread(sellTicket01);

        //启动线程
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
class SellTicket01 implements Runnable{

    private static int ticketNum = 100;//这里使用了static，让多个线程共享。
    private boolean loop = true;

    public synchronized void sell(){
        if (ticketNum <= 0){
            System.out.println("售票结束--");
            loop = false;
            return;
        }
        //休眠50ms
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票 " + "还剩票数 " + (--ticketNum));
    }

    @Override
    public void run() {
        while (loop){
            sell();
        }
    }
}