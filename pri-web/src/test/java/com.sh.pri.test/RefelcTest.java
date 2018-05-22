package com.sh.pri.test;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 泛型 test
 */
public class RefelcTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("abc");
        Class<? extends ArrayList> c1 = list.getClass();
        Class<? extends ArrayList> c2 = list1.getClass();

        System.out.println(c1==c2); //true    说明编译之后是去泛型化的, java中的泛型只在编译期有效

        try {
            //绕过编译去操作  就绕过了泛型的限制
            Method method = c2.getMethod("add", Object.class);
            Object invoke = method.invoke(list1, 20);
            System.out.println(invoke);  //true
            System.out.println(list1.size());//2
            System.out.println(list1); //[abc, 20]

            for (String s : list1) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
