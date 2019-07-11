package com.dnp.http.readFile;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by huazai on 2018/12/21.
 */
public class ReadFile {
    public static void main(String[] args) {
        try {
           String fileContent =  FileUtils.readFileToString(new File("C:\\Users\\huazai\\Desktop\\backupCQ.sql"), "utf-8");
//            System.out.println("fileContent = " + fileContent);
            int xx = fileContent.indexOf("INSERT IGNORE INTO `users` (`id`, `userid`, `username`, `avatar`, `secret`, `defaultchannel`, `enable`, `type`, `createid`, `creattime`, `maturitytime`, `imeiid`, `lastlogintime`, `email`, `uuid`) VALUES");
            System.out.println("xx = " + xx);
            int yy = fileContent.indexOf("/*!40000 ALTER TABLE `users` ENABLE KEYS */;");
            System.out.println("yy = " + yy);
            String insertData = fileContent.substring(xx, yy);
            System.out.println("insertData = " + insertData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
