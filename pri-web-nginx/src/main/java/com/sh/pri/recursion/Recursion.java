package com.sh.pri.recursion;


/**
 * 递归重要的是找到规律,并给他一次出口(结束条件)
 * Created by admin on 2018/1/12.
 * <p>
 * 一般而言，兔子在出生两个月后，就有繁殖能力，一对兔子每个月能生出一对小兔子来。
 * 如果所有兔子都不死，那么若干月以后可以繁殖多少对兔子？
 * n月份:1    2   3   4   5
 *       1    1   2   3   5
 * n>2时:f(n)=f(n-1)+f(n-2)
 * 写递归要先找出其中的规律
 */
public class Recursion {
    public static void main(String[] args) {
        int a =8;//月份
        System.out.println(breed(a));
    }
    public static int breed(int a){
        int count;//兔子总数
        //递归的出口,结束条件
        if (a < 3 && a > 0){
            return 1;
        }
        //递归的规律,公式
        count = breed(a - 1)+breed(a-2);
        return count;
    }
}
