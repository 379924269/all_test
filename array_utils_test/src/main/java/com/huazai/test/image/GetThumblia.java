package com.huazai.test.image;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 华仔
 * @date 2018/7/27 15:08
 */
public class GetThumblia {
    public static void main(String[] args) throws Exception {
        processImg("http://video.699pic.com/videos/79/53/73/mWXcVs6K9gEh1512795373.mp4", "E:\\06\\1.jpg");
    }

    /**
     * 获取视频缩略图
     *
     * @param video_path 视频路径，如：c://aa.mp4 或 http://test.delinp.cn:8001/video/66.mp4
     * @param image_path 缩略图存放路径
     * @return boolean true： 成功，false：失败
     */
    public static boolean processImg(String video_path, String image_path) {
        if (new File(image_path).exists()) return true;
        List<String> commend = new java.util.ArrayList<String>();
        commend.add("ffmpeg");
        commend.add("-i");
        commend.add(video_path);
        commend.add("-y");
        commend.add("-f");
        commend.add("image2");
        commend.add("-t");
        commend.add("0.001");
        commend.add("-s");
        commend.add("320x240");
        commend.add(image_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取视频播放时间
     *
     * @param video_path 视频路径，如：c://aa.mp4 或 http://test.delinp.cn:8001/video/66.mp4
     * @return
     */
    public static int getVideoTime(String video_path) {
        List<String> commands = new java.util.ArrayList<String>();
        commands.add("E:\\Program Files\\ffmpeg-20180726-bce4da8-win32-static\\bin\\ffmpeg");
        commands.add("-i");
        commands.add(video_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            final Process p = builder.start();

            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            //从视频信息中解析时长
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(sb.toString());
            if (m.find()) {
                int time = getTimelen(m.group(1));
                System.out.println(video_path + ",视频时长：" + time + ", 开始时间：" + m.group(2) + ",比特率：" + m.group(3) + "kb/s");
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    //格式:"00:00:10.68"
    private static int getTimelen(String timelen) {
        int min = 0;
        String strs[] = timelen.split(":");
        if (strs[0].compareTo("0") > 0) {
            min += Integer.valueOf(strs[0]) * 60 * 60;//秒
        }
        if (strs[1].compareTo("0") > 0) {
            min += Integer.valueOf(strs[1]) * 60;
        }
        if (strs[2].compareTo("0") > 0) {
            min += Math.round(Float.valueOf(strs[2]));
        }
        return min;
    }
}
