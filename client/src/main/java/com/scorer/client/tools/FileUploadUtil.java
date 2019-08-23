package com.scorer.client.tools;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.scorer.client.tools.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 用于文件上传的通用工具类
 */
public class FileUploadUtil {
    /**
     * 上传到七牛云服务的两个凭证
     */
    private static String accessKey = "mZKIJzAVoytzbo9YhSKaKv_ntgb2BB3oTAQu4SZw";
    private static String secretKey = "cUP9k_G-5bEBcAicBCajU8d9D3laPIhhrRZ2yTBY";
    //qzdimage的外链地址的头部分
    private  static String imageUrlLink="http://image.balledu.com/";

    /**
     * 文件上传
     * @param fileName 文件名称
     * @param bucket 云存储的空间名称[存储图片为：qzdimage]
     * @param file 需要上传的文件
     * @return
     */
    public static String fileUpload(String fileName,String bucket,MultipartFile file){
        //构造一个带指定Zone对象的配置类,指定文件传输地区为华南地区
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        //定义外链接地址
        String urlLink="";
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(uploadBytes, fileName, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                urlLink=imageUrlLink+putRet.key;
                System.out.println(urlLink);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urlLink;
    }
    public static String fileUploadByte(String fileName,String bucket,byte[] file){
        //构造一个带指定Zone对象的配置类,指定文件传输地区为华南地区
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        //定义外链接地址
        String urlLink="";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(file, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            urlLink=imageUrlLink+putRet.key;
            System.out.println(urlLink);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return urlLink;
    }
    public static String fileUploadByFilePath(String filePath){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_");
        String uuid = UUID.randomUUID().toString().replace("-","").toUpperCase();
        String suffix = filePath.substring(filePath.lastIndexOf("."));
        String photoName = sdf.format(new Date()).concat(uuid + suffix);
        File imgFile = new File(filePath);
        String urlPath = null;
        try {
            InputStream input = new FileInputStream(imgFile);
            byte[] byt = new byte[input.available()];
            if(input.read(byt)!=-1)
                urlPath = com.scorer.client.tools.FileUploadUtil.fileUploadByte(photoName, "qzdimage", byt);
            input.close();
            imgFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlPath;
    }
    public static String fileUploadDirect(MultipartFile file){
        if(!ObjectUtils.isEmpty(file)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_");
            String uuid = UUID.randomUUID().toString().replace("-","").toUpperCase();                                                               //生成唯一标识符
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));   //获取文件后缀
            String photoName = sdf.format(new Date()).concat(uuid + suffix);                                          //生成新文件名
            return fileUpload(photoName, "qzdimage", file);
        }else{
            return null;
        }
    }

}
