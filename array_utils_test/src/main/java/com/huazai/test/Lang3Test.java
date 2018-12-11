package com.huazai.test;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author 华仔
 * @date 2018/6/25 9:39
 */
public class Lang3Test {

    public static void main(String[] args) {
        String newPwd = "21321aa";
        int i = newPwd.matches(".*\\d+.*") ? 1 : 0;
        int j = newPwd.matches(".*[a-zA-Z]+.*") ? 1 : 0;
        int k = newPwd.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? 1 : 0;
        System.out.println("k = " + k);
        System.out.println("j = " + j);
        System.out.println("i = " + i);
    }
}
