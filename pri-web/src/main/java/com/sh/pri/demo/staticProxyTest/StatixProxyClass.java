package com.sh.pri.demo.staticProxyTest;


/**
 * 静态代理
 * Created by Administrator on 2018/3/12.
 */
public class StatixProxyClass implements BasicInterface{
    //把代理类对象传进来
    private Vendor vendor;
    public StatixProxyClass(Vendor vendor){
        this.vendor = vendor;
    }
    @Override
    public void add() {
        System.out.println("静态代理......befor");
        vendor.add();
        System.out.println("静态代理......after");
    }

    @Override
    public void sell() {

    }
}
