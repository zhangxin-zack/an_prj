package com.scorer.feign.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SaveFile {

    public static String saveMultipartFile(MultipartFile file,String savePath){
        File temp_path = new File("");
        savePath=temp_path.getAbsolutePath()+savePath;
        String file_path = savePath+File.separator+file.getOriginalFilename();
        File temp = new File(file_path);
        File tempPath = new File(savePath);
        if(!tempPath.exists()){
            boolean mkdirs = tempPath.mkdirs();
            if(!mkdirs){
                System.err.println("创建文件路径失败!");
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(temp);
            InputStream inputStream = file.getInputStream();
            byte[] getData = readInputStream(inputStream);
            fos.write(getData);
            fos.close();
            inputStream.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return file_path;
    }

    private static byte[] readInputStream(InputStream inputStream) throws Throwable {
        byte[] buffer = new byte[1024000];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}
