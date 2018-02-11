package com.main;

import sun.misc.PostVMInitHook;

import javax.sound.midi.Soundbank;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by admin on 2018/2/11.
 */
public class Test {
    public static void main(String[] args) {
//        AtomicLong atomicLong = new AtomicLong(21);
//        long l = atomicLong.incrementAndGet();
//        System.out.println(l);
        String a = "hello, %s!";
        String b = String.format(a, "zhangsan");
        System.out.println(b);
    }
}
