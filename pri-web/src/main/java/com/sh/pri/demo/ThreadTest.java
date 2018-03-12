package com.sh.pri.demo;

public class ThreadTest extends Thread{
	 private String threadName;
	public ThreadTest(String threadName) {
		super();
		this.threadName = threadName;
	}
	//重写run方法
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(threadName + "线程运行 : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadTest a = new ThreadTest("A");
		ThreadTest b = new ThreadTest("B");
		//启动线程
		//start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。
		a.start();
		b.start();
	}
}
//class Go1 {
//	public static void main(String[] args) {
//		ThreadTest a = new ThreadTest("A");
//		ThreadTest b = new ThreadTest("B");
//		a.start();
//		b.start();
//	}
//}