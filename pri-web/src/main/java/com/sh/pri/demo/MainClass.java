package com.sh.pri.demo;

import com.sh.pri.demo.DynamicProxyTest.DynamicProxyAnonymousInnerClasses;
import com.sh.pri.demo.DynamicProxyTest.DynamicProxyClass;
import com.sh.pri.demo.staticProxyTest.BasicInterface;
import com.sh.pri.demo.staticProxyTest.StatixProxyClass;
import com.sh.pri.demo.staticProxyTest.Vendor;

/**
 * Created by Administrator on 2018/3/12.
 */
public class MainClass {
    public static void main(String[] args) {
        //静态代理
//        StatixProxyClass statixProxyClass = new StatixProxyClass(new Vendor());
//        statixProxyClass.add();

        //动态代理
//        DynamicProxyClass dynamicProxyClass = new DynamicProxyClass(new Vendor());
//        BasicInterface proxy = (BasicInterface) dynamicProxyClass.getProxy();
//        proxy.add();

        //动态代理  匿名内部类实现
        DynamicProxyAnonymousInnerClasses cc = new DynamicProxyAnonymousInnerClasses(new Vendor());
        BasicInterface proxy = (BasicInterface) cc.getProxy();
        proxy.add();
    }
}
