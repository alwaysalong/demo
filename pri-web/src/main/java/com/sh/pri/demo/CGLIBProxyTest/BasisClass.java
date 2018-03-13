package com.sh.pri.demo.CGLIBProxyTest;

/**
 * CGLIB动态代理:委托类
 * Created by Administrator on 2018/3/13.
 */
public class BasisClass {

    public void add(){
        System.out.println("委托类.....add()...");
    }

    public void sell(){
        System.out.println("委托类.....sell()...");
    }
}
