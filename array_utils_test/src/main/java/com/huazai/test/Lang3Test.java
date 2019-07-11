package com.huazai.test;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.*;

/**
 * @author 华仔
 * @date 2018/6/25 9:39
 */
public class Lang3Test {

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("i = " + i);
                }
            }
        };

        r.run();
    }
}
