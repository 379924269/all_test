package com.huazai.test;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 华仔
 * @date 2018/6/25 14:13
 */
public class IoTest {

    public static void main(String[] args) {
        //File file = new File(".");
        //String filPath = file.getAbsolutePath();
        //
        //System.out.println("filPath = " + filPath);
        //
        //String[] fileArray = file.list();
        //for (int i = 0; i < fileArray.length; i++) {
        //    System.out.println(fileArray[i]);
        //}
        //
        //System.out.println("\n");
        //
        //String[] fileArray1 = file.list(new SizeFileFilter(20, false));
        //for (int i = 0; i < fileArray1.length; i++) {
        //    System.out.println(fileArray1[i]);
        //}
        //
        //try {
        //    FileUtils.copyFileToDirectory(new File("E:\\ideaGitProject\\all_test\\README.md"),
        //            new File("E:\\00\\"));
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        //
        //try {
        //    FileUtils.copyURLToFile(new URL("https://gss0.baidu.com/8_BXsjip0QIZ8tyhnq/timg?wh_rate=0&wapiknow&quality=100&size=w250&sec=0&di=57e2e21e9f5d27b00c32b90162f2f227&src"
        //                    + "=http%3A%2F%2Fc.hiphotos.baidu" +
        //                    ".com%2Fzhidao%2Fwh%253D800%252C450%253B%2Fsign%3Db50d1863b88f8c54e386cd270a1901c1%2Fdbb44aed2e738bd4a9406cbcad8b87d6267ff9de.jpg")
        //            , new File("e:\\00\\a.jpg"));
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

    }

    class User implements Serializable{
        int id;
        int ad;
        String cc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAd() {
            return ad;
        }

        public void setAd(int ad) {
            this.ad = ad;
        }

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }
    }




    public void acceptFile(File file) {

        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setAd(1);

    }
}
