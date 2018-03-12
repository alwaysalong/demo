package com.sh.pri.demo.DynamicProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理 的匿名内部类实现
 * Created by Administrator on 2018/3/12.
 */
public class DynamicProxyAnonymousInnerClasses {
    private Object obj;
    public DynamicProxyAnonymousInnerClasses(Object obj){
        this.obj = obj;
    }
    public Object getProxy(){
        Object o = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("动态代理,匿名内部类实现.....befor...");
                Object result = method.invoke(obj, args);
                System.out.println("动态代理,匿名内部类实现.....after...");
                return result;
            }
        });
        return o;
    }
}
