package com.huazai.test.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

/**
 * 该类是图片处理类
 *
 * @author
 *
 */
public final class ImageUtil {
    /** 图片格式：JPG */
    private static final String PICTRUE_FORMATE_JPG = "jpg";

    private ImageUtil() {
    }
    /**
     * 生成组合头像
     *
     * @param paths
     *            用户图像
     * @param dir
     * @param groupId
     * @return
     * @throws IOException
     */
    public static boolean getCombinationOfhead(List<String> paths, String dir, String groupId)
            throws IOException {
        List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
        // 压缩图片所有的图片生成尺寸同意的 为 50x50
        for (int i = 0; i < paths.size(); i++) {
            BufferedImage resize2 = ImageUtil.resize2(paths.get(i), 40, 40, true);
            bufferedImages.add(resize2);
        }

        int width = 112; // 这是画板的宽高

        int height = 112; // 这是画板的高度

        // BufferedImage.TYPE_INT_RGB可以自己定义可查看API

        BufferedImage outImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 生成画布
        Graphics g = outImage.getGraphics();

        Graphics2D g2d = (Graphics2D) g;

        // 设置背景色
        g2d.setBackground(new Color(231,231,231));

        // 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
        g2d.clearRect(0, 0, width, height);

        // 开始拼凑 根据图片的数量判断该生成那种样式的组合头像目前为4中
        int j = 1;
        for (int i = 1; i <= bufferedImages.size(); i++) {
            if (bufferedImages.size()==9) {
                if (i<=3) {
                    g2d.drawImage(bufferedImages.get(i - 1), 30 * i + 3 * i
                            - 31, 0, null);
                }else if(i<=6) {
                    g2d.drawImage(bufferedImages.get(i - 1),27 * j + 5 * j
                            - 30, 36, null);
                    j++;
                }else{
                    g2d.drawImage(bufferedImages.get(i - 1),27 * j + 5 * j-125, 77, null);
                    j++;
                }
            }
            else if (bufferedImages.size()== 8) {
                if (i<=3) {
                    g2d.drawImage(bufferedImages.get(i - 1), 30 * i + 3 * i
                            - 31, 0, null);
                }else if(i<=6) {
                    g2d.drawImage(bufferedImages.get(i - 1),27 * j + 5 * j
                            - 30, 36, null);
                    j++;
                }else{
                    g2d.drawImage(bufferedImages.get(i - 1),21*j+9*j-100, 77, null);
                    j++;
                }

            }

            else if (bufferedImages.size()== 7) {
                if (i<=3) {
                    g2d.drawImage(bufferedImages.get(i - 1), 30 * i + 3 * i
                            - 31, 0, null);
                }else if(i<=6) {
                    g2d.drawImage(bufferedImages.get(i - 1),27 * j + 5 * j
                            - 30, 36, null);
                    j++;
                }else{
                    g2d.drawImage(bufferedImages.get(i - 1), 3, 75, null);
                }

            }


            else  	if (bufferedImages.size()== 6) {
                if (i<=3) {
                    g2d.drawImage(bufferedImages.get(i - 1), 30 * i + 3 * i
                            - 31, 10, null);
                }else {
                    g2d.drawImage(bufferedImages.get(i - 1), 31 * j + 4 * j
                            - 33, 60, null);
                    j++;
                }
            }
            else if (bufferedImages.size()== 5) {
                if (i<=3) {
                    g2d.drawImage(bufferedImages.get(i - 1), 30 * i + 3 * i
                            - 28, 10, null);
                }else {
                    g2d.drawImage(bufferedImages.get(i - 1), 48 * j + 8 * j
                            - 49, 60, null);
                    j++;
                }

            }
            else if (bufferedImages.size()== 4) {
                if (i <= 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i
                            - 50, 4, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j
                            - 50, 58, null);
                    j++;
                }
            } else if (bufferedImages.size() == 3) {
                if (i <= 1) {

                    g2d.drawImage(bufferedImages.get(i - 1), 31, 4, null);

                } else {

                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j
                            - 50, 58, null);

                    j++;
                }

            } else if (bufferedImages.size() == 2) {

                g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i - 50,
                        31, null);

            } else if (bufferedImages.size() == 1) {

                g2d.drawImage(bufferedImages.get(i - 1), 31, 31, null);

            }

            // 需要改变颜色的话在这里绘上颜色。可能会用到AlphaComposite类
        }
//        new File(dir + "groupPicture" + File.separatorChar
//				+ groupId.substring(0, 4) + File.separatorChar + groupId+".jpg")
//        String outPath = dir+groupId+".jpg";
        StringBuffer outPath = new StringBuffer().append(dir)
                .append("groupPicture")
                .append(File.separatorChar)
                .append(groupId.substring(0, 4))
                .append(File.separatorChar)
                .append(groupId).append(".jpg");

        System.out.println("outPath = " + outPath);

        String format = "JPG";
        File file = new File(outPath.toString());
        if(!file.exists()){
            file.mkdirs();
        }

        System.out.println(file.getAbsolutePath());

        return ImageIO.write(outImage, format, file);
    }

    /**
     * 图片缩放
     *
     * @param filePath
     *            图片路径
     * @param height
     *            高度
     * @param width
     *            宽度
     * @param bb
     *            比例不对时是否需要补白
     */
    public static BufferedImage resize2(String filePath, int height, int width,
                                        boolean bb) {
        try {
            double ratio = 0; // 缩放比例
//            System.out.println("图片缩放"+filePath);
            BufferedImage bi =null;
            if(filePath.indexOf("http://")==0){
                bi = ImageIO.read(new URL(filePath));
            }else{
                bi = ImageIO.read(new File(filePath));
            }
            Image itemp = bi.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(
                        AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {
                // copyimg(filePath, "D:\\img");
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            return (BufferedImage) itemp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static StringBuffer httpsRequest(String requestUrl, String dir) throws NoSuchAlgorithmException, NoSuchProviderException,
            KeyManagementException, MalformedURLException, IOException,
            ProtocolException, UnsupportedEncodingException {

        URL url = new URL(requestUrl);


        InputStream connection1 = url.openStream();


        try {
            byte[] bb=readInputStream(connection1);
            writeImageToDisk(bb,dir+".jpg");
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    public static void writeImageToDisk(byte[] img, String fileName){
        try {
            File file = new File(fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        List<String> picUrls = new ArrayList<String>();
        picUrls.add("C:\\Users\\Dick\\Pictures\\th.jpg");
        picUrls.add("C:\\Users\\Dick\\Pictures\\th.jpg");
        picUrls.add("C:\\Users\\Dick\\Pictures\\th.jpg");
        picUrls.add("C:\\Users\\Dick\\Pictures\\th.jpg");
        //picUrls.add("C:\\Users\\Dick\\Pictures\\th.jpg");
        //picUrls.add("C:\\Users\\Dick\\Pictures\\th.jpg");
        //picUrls.add("C:\\Users\\Dick\\Pictures\\th.jpg");
        //picUrls.add("C:\\Users\\Dick\\Pictures\\th.jpg");
        //picUrls.add("C:\\Users\\Dick\\Pictures\\th.jpg");
        String dir = ".\\";
        String groupId = "123456";
        getCombinationOfhead(picUrls, dir ,groupId);

    }
}

