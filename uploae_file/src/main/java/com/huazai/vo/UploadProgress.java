package com.huazai.vo;

/**
 * 文件上传进度
 *
 * @author 华仔
 * @date 2018/6/29 16:02
 */
public class UploadProgress {
    private long total;
    private long read;
    private String readPercent;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getRead() {
        return read;
    }

    public void setRead(long read) {
        this.read = read;
    }

    public String getReadPercent() {
        return readPercent;
    }

    public void setReadPercent(String readPercent) {
        this.readPercent = readPercent;
    }
}
