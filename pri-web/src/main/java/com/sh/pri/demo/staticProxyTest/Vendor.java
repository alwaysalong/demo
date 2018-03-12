package com.sh.pri.demo.staticProxyTest;


/**
 * Created by Administrator on 2018/3/12.
 */
public class Vendor implements BasicInterface {
    @Override
    public void add() {
        System.out.println("委托类   add()...");
    }

    @Override
    public void sell() {
        System.out.println("委托类    sell()...");
    }
}
