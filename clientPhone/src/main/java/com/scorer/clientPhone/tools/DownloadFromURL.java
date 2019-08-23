package com.scorer.clientPhone.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadFromURL {

    /**
     * 从网络Url中获取字节数组
     * @param urlStr
     */
    public static byte[] getByte(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            if(url!=null){
                conn = (HttpURLConnection)url.openConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置超时间为3秒
        if(conn!=null){
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
        }
        InputStream inputStream = null;
        try {
            if(conn!=null){
                inputStream = conn.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        if(inputStream!=null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("info:"+url+" download success");
        return getData;
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     */
    private static byte[] readInputStream(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int len;
        boolean ck = false;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                ck=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] reByte = ck?bos.toByteArray():null;
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reByte;
    }
}
