package com.scorer.client.tools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.exception.ObsException;
import org.springframework.web.multipart.MultipartFile;

/**
 * This sample demonstrates how to do object-related operations
 * (such as create/delete/get/copy object, do object ACL/OPTIONS)
 * on OBS using the OBS SDK for Java.
 */
public class HuaWeiOBS {
    private static final String endPoint = "https://obs.cn-north-4.myhuaweicloud.com";
    private static final String ak = "C1VOY2CSV5PBE8TOAJUW";
    private static final String sk = "llKesyHCuNXwlMGnFzww9hJMMhSbX23MS43OtAMx";
    private static ObsClient obsClient;
    private static String bucketName = "mx-obs-eed8";
    private static final String domain = "https://mx-obs-eed8.obs.cn-north-4.myhuaweicloud.com/";

    private static String fileUpload(String fileName, byte[] file) {
        ObsConfiguration config = new ObsConfiguration();
        config.setSocketTimeout(30000);
        config.setConnectionTimeout(10000);
        config.setEndPoint(endPoint);
        try {
            obsClient = new ObsClient(ak, sk, config);
            obsClient.putObject(bucketName, fileName, new ByteArrayInputStream(file), null);
            System.out.println("Create object:" + fileName + " successfully!");
            return domain+fileName;
        } catch (ObsException e) {
            System.out.println("Response Code:" + e.getResponseCode());
            System.out.println("Error Message:" + e.getErrorMessage());
            System.out.println("Error Code:" + e.getErrorCode());
            System.out.println("Request ID:" + e.getErrorRequestId());
            System.out.println("Host ID:" + e.getErrorHostId());
            return null;
        } finally {
            if (obsClient != null) {
                try {
                    obsClient.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static String fileUpload(MultipartFile file){
        if(!ObjectUtils.isEmpty(file)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_");
            String uuid = UUID.randomUUID().toString().replace("-","").toUpperCase();                                                               //生成唯一标识符
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));   //获取文件后缀
            String photoName = sdf.format(new Date()).concat(uuid + suffix);                                          //生成新文件名
            try {
                return fileUpload(photoName, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

}
