package com.sh.pri.threadDemo;

/*
 * 如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享。
 * 实现Runnable接口比继承Thread类所具有的优势：
 *	1）：适合多个相同的程序代码的线程去处理同一个资源
 *	2）：可以避免java中的单继承的限制
 *	3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立
 *	4）：线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的类
 *
 *
 *在java中，每次程序运行至少启动2个线程。一个是main线程，一个是垃圾收集线程。
 *因为每当使用java命令执行一个类的时候，实际上都会启动一个ＪＶＭ，每一个ｊＶＭ实习在就是在操作系统中启动了一个进程。
 *
 */
public class ThreadTest1 implements Runnable{

	private String name;
	public ThreadTest1( String name){
		this.name = name;
	}
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(name + "线程运行  : " + i);
			try {
				//线程随机睡眠0-10ms
				Thread.sleep((int)(Math.random()*10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args) {
		ThreadTest1 a = new ThreadTest1("A");
		ThreadTest1 b = new ThreadTest1("B");
		//实现Runnable接口方式的多线程  启动方式和继承Tread类不太一样
		//在启动的多线程的时候，需要先通过Thread类的构造方法Thread(Runnable target) 构造出对象，然后调用Thread对象的start()方法来运行多线程代码。
		new Thread(a).start();
		new Thread(b).start();
	}
	
}
