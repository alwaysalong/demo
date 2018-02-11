package com.sh.pri.controller;

/**
 * Created by admin on 2018/1/26.
 * 有三个线程T1 T2 T3，如何保证他们按顺序执行-转载
 * 在T2的run中，调用t1.join，让t1执行完成后再让T2执行
 * 在T2的run中，调用t2.join，让t2执行完成后再让T3执行
 */
public class ThreadTest {
    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                t2.join();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1");

        }
    });
    Thread t2 = new Thread(new Runnable() {

        @Override
        public void run() {
            try {
                t3.join();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        }
    });
    Thread t3 = new Thread(new Runnable() {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3");
        }
    });
    public static void main(String[] args) {
        ThreadTest testA = new ThreadTest();
        testA.t1.start();
        testA.t2.start();
        testA.t3.start();
    }
}
