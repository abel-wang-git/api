package com.wanghuiwen.common;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VideoScreenshotUtil {

    static Logger logger = LoggerFactory.getLogger("VideoScreenshotUtil");



    /**
     * @Description: 获取视频截图
     * @throws IOException  void
     */
    public static void getScreenshot(File sourceFile, File targetImage) {
        logger.debug("截取视频截图开始："+ System.currentTimeMillis());
        try {

        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(sourceFile);

        grabber.start();
        //设置视频截取帧（默认取第一帧）
        Frame frame = grabber.grabImage();

        //视频旋转度

        Java2DFrameConverter converter = new Java2DFrameConverter();
        //绘制图片
        BufferedImage bi = converter.getBufferedImage(frame);

        /**
         * 因为手机拍摄的视频中存在 rotate(即旋转度的原因)
         */
        String rotate = grabber.getVideoMetadata("rotate");
        if (rotate != null) {
            // 旋转图片
            bi = rotate(bi, Integer.parseInt(rotate));
        }

        //图片的类型
        String imageMat = "jpg";
        ImageIO.write(bi, imageMat, targetImage);

        long duration = grabber.getLengthInTime() / (1000 * 1000);

        logger.debug("视频的宽:" + bi.getWidth());
        logger.debug("视频的高:" + bi.getHeight());
        logger.debug("视频的旋转度：" + rotate);
        logger.debug("视频的格式：" + grabber.getFormat());
        logger.debug("此视频时长（s/秒）：" + duration);
        grabber.stop();

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("截取视频截图结束："+ System.currentTimeMillis());
    }

    /**
     * @Description: 根据视频旋转度来调整图片
     * @param src
     * @param angel	视频旋转度
     * @return  BufferedImage
     */
    public static BufferedImage rotate(BufferedImage src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        int type = src.getColorModel().getTransparency();
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
        BufferedImage bi = new BufferedImage(rect_des.width, rect_des.height, type);
        Graphics2D g2 = bi.createGraphics();
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return bi;
    }

    /**
     * @Description: 计算图片旋转大小
     * @param src
     * @param angel
     * @return  Rectangle
     */
    public static Rectangle calcRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }
}
