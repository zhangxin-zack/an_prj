package com.scorer.clientPhone.tools;

import com.scorer.clientPhone.values.CodeForFont;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

public class GenerateCode {

    public String createIMG(Integer[] code){
        int width = 120;
        int height = 30;
        // 1.在内存中构建出一个图片。
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 2.获得图像上的画布以及设置颜色边框。
        Graphics2D gra = (Graphics2D) image.getGraphics();
        // 3.画布设置颜色
        gra.setBackground(Color.LIGHT_GRAY);
        gra.fillRect(0, 0, width, height);
        // 4.设置边框
        gra.setColor(Color.blue);
        gra.drawRect(0, 0, width - 1, height - 1);
        // 5.一般在验证图片上都会有一些干扰线，在这里随机生成5跟长度随机的线条。
        for (int i = 0; i < 5; i++) {
            gra.setColor(Color.RED);
            gra.drawLine(randomNum(0, width), randomNum(0, height), randomNum(0, width), randomNum(0, height));
        }
        // 6.在画布上水机写上常用的中文
        for (int i = 0; i < code.length; i++) {
            gra.setColor(new Color(randomNum(0, 255), randomNum(0, 255),
                    randomNum(0, 255)));
            gra.setFont(new Font("黑体", Font.BOLD, 20));
            // 字体旋转
            int r = randomNum(-30,30);
            gra.rotate(1.0*Math.PI*r/180,30 * i, 22);
            String str= CodeForFont.BaseFont.charAt(code[i])+"";
            gra.drawString(str, 30 * i+5 * i, 22);
            gra.rotate(-1.0*Math.PI*r/180,30 * i, 22);
        }
        return encode(image);
    }

    public String createIMG_CODE(Integer[] code){
        int width = 120;
        int height = 30;
        // 1.在内存中构建出一个图片。
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 2.获得图像上的画布以及设置颜色边框。
        Graphics2D gra = (Graphics2D) image.getGraphics();
        // 3.画布设置颜色
        gra.setBackground(Color.LIGHT_GRAY);
        gra.fillRect(0, 0, width, height);
        // 4.设置边框
        gra.setColor(Color.blue);
        gra.drawRect(0, 0, width - 1, height - 1);
        // 5.一般在验证图片上都会有一些干扰线，在这里随机生成5跟长度随机的线条。
        for (int i = 0; i < 5; i++) {
            gra.setColor(Color.RED);
            gra.drawLine(randomNum(0, width), randomNum(0, height), randomNum(0, width), randomNum(0, height));
        }
        // 6.在画布上水机写上常用的中文
        for (int i = 0; i < code.length; i++) {
            gra.setColor(new Color(randomNum(0, 255), randomNum(0, 255),
                    randomNum(0, 255)));
            gra.setFont(new Font("黑体", Font.BOLD, 20));
            // 字体旋转
            int r = randomNum(-45,45);
            gra.rotate(1.0*Math.PI*r/180,30 * i, 22);
            String str= code[i]+"";
            gra.drawString(str, 30 * i, 22);
            gra.rotate(-1.0*Math.PI*r/180,30 * i, 22);
        }
        return encode(image);
    }

    private byte[] initByteArray(BufferedImage image){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] r =null;
        try {
            ImageIO.write(image,"jpg",out);
            r = out.toByteArray();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }
    private void showFile(BufferedImage image,String path){
        try {
            FileOutputStream fops = new FileOutputStream(new File(path));
            ImageIO.write(image,"jpg",fops);
            fops.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String encode(BufferedImage image) {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final byte[] imgByte = initByteArray(image);
        //编码
        final String encodedText = encoder.encodeToString(imgByte);
        //解码
        byte[] decode = decoder.decode(encodedText);
        return encodedText;
    }

    private int randomNum(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }

}
