package com.scorer.clientPhone.controller;

import com.google.gson.Gson;
import com.scorer.clientPhone.tools.DownloadFromURL;
import com.scorer.clientPhone.tools.SaveFile;
import com.scorer.clientPhone.tools.ObjectUtils;
import com.scorer.clientPhone.values.ResultMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/Test")
public class Test {

    @RequestMapping(value = "/TestNetSRC")
    public ResponseEntity<byte[]> TestNetSRC(HttpServletResponse response, HttpServletRequest request) {
        byte[] res = DownloadFromURL.getByte("http://image.cpwreferee.com/20181218030256e986ae8d-d779-4e64-873c-94d360f73823.mp4");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/octet-_Stream");
        headers.add("Connection", "close");
        headers.add("Accept-Ranges", "bytes");
        headers.add("Content-Disposition", "attachment;filename=123.mp4");
        Cookie[] cookies = request.getCookies();
        System.out.println(new Gson().toJson(cookies));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Cookie cookie_token = new Cookie("cookie_test"+new Random().nextInt(10), simpleDateFormat.format(new Date()));
        cookie_token.setMaxAge(24 * 60 * 60); //秒
        cookie_token.setPath("/");
        response.addCookie(cookie_token);
        return new ResponseEntity<>(res, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/TestLocalSRC")
    public void TestLocalSRC(HttpServletResponse response) throws IOException {
        File file = new File("D:\\123.mp4");
        InputStream input = new FileInputStream(file);
        byte[] aByte = new byte[input.available()];
        input.read(aByte);
        response.getOutputStream().write(aByte);
    }

    @RequestMapping(value = "/TestUpload")
    @ResponseBody
    public Map TestUpload(@RequestParam(value = "photo_file", required = false) MultipartFile photo_file) {
        Map<String, Object> responseMap = new HashMap<>();
        System.out.println("in Upload");
        if (!ObjectUtils.isEmpty(photo_file)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd\\HHmmss");
            Calendar calendar = Calendar.getInstance();
            String name = sdf.format(calendar.getTime());
            SaveFile.saveMultipartFile(photo_file, "\\TEMP\\" + name);
            responseMap.put("result", 1);
        } else {
            System.err.println("photo_file is empty");
            responseMap.put("result", 3010);
        }
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }

}