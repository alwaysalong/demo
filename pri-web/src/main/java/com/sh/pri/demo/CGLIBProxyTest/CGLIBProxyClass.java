package com.sh.pri.demo.CGLIBProxyTest;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理: 代理类
 * Created by Administrator on 2018/3/13.
 */
public class CGLIBProxyClass implements MethodInterceptor {
    private Object obj;
    public CGLIBProxyClass(Object obj){
        this.obj = obj;
    }
    public Object getProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object o = enhancer.create();
        return o;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB动态代理.....befor...");//一般可以做一些权限校验的逻辑
        Object invoke = method.invoke(obj, objects);
        System.out.println("CGLIB动态代理.....after...");
        return invoke;
    }
}
