package com.zhuzhustudy.thread;

/**
 * @author 朱晨华
 */
public class Exercise02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        //这里就不能调用start方法了
        //需要创建Thread对象，把dog对象（实现了Runnable接口），放入Thread
        Thread thread = new Thread(dog);//代理模式
        thread.start();
    }
}
class Dog implements Runnable{
    int count = 0;
    @Override
    public void run() {
        while (true){
            System.out.println("小狗汪汪叫--" + ++count + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 10){
                break;
            }
        }
    }
}