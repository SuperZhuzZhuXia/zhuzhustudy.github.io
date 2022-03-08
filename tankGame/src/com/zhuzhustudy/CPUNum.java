package com.zhuzhustudy;

/**
 * @author 朱晨华
 */
public class CPUNum {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        //获取当前电脑CPU个数
        int cpuNums = runtime.availableProcessors();
        System.out.println("当前电脑CPU个数：" + cpuNums);
    }
}
