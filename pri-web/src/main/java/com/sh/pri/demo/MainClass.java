package com.sh.pri.demo;

import com.sh.pri.demo.CGLIBProxyTest.BasisClass;
import com.sh.pri.demo.CGLIBProxyTest.CGLIBProxyClass;
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
//        DynamicProxyAnonymousInnerClasses cc = new DynamicProxyAnonymousInnerClasses(new Vendor());
//        BasicInterface proxy = (BasicInterface) cc.getProxy();
//        proxy.add();

        //CGLIB动态代理   需要引入cglib的依赖包   spring的依赖包里已经包含了
        CGLIBProxyClass cglibProxyClass = new CGLIBProxyClass(new BasisClass());
        BasisClass proxy = (BasisClass) cglibProxyClass.getProxy();
        proxy.add();
        proxy.sell();
    }
}
