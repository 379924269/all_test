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
        try {
            Date date = DateUtils.parseDate("2014-01-10 10:10:10", "yyyy-MM-dd hh:mm:ss");
            System.out.println(DateUtils.addDays(date, 2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
