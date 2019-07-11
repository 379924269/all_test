package com.huazai.test.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huazai on 2019/1/15.
 */
public class IpUtils {
    /**
     * 所验证的IP是否在ipSection段内， IP段（以'-'分隔）,param like:
     * String ip = "10.164.65.0"
     * String ipSection="10.163.64.00-10.163.71.255";
     *
     * @param ip        所验证的IP
     * @param ipSection IP段（以'-'分隔）
     * @return true 在ip段内， false：不在ip段内
     */
    public static boolean ipExistsInRange(String ip, String ipSection) {
        ipSection = ipSection.trim();
        ip = ip.trim();
        int idx = ipSection.indexOf('-');
        String beginIP = ipSection.substring(0, idx);
        String endIP = ipSection.substring(idx + 1);
        return getIp2long(beginIP) <= getIp2long(ip) && getIp2long(ip) <= getIp2long(endIP);

    }

    /**
     * 把ip变成long
     *
     * @param ip ip地址
     * @return long
     */
    public static long getIp2long(String ip) {
        ip = ip.trim();
        String[] ips = ip.split("\\.");
        long ip2long = 0L;
        for (int i = 0; i < 4; ++i) {
            ip2long = ip2long << 8 | Integer.parseInt(ips[i]);
        }
        return ip2long;

    }

    /**
     * 获取ip地址
     *
     * @param request HttpServletRequest
     * @return string ip地址
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void main(String[] args) {
        System.out.println("=================");
    }


}
