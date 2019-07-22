package com.scorer.feign.controller;

import com.scorer.feign.feign_con.TestFeignCon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class TestController {

    @Resource
    private TestFeignCon testFeignCon;

    @RequestMapping(value = "/Test/TestNetSRC")
    public ResponseEntity<byte[]> TestNetSRC(){
        return testFeignCon.TestNetSRC();
    }

    @RequestMapping(value = "/Test/TestUpload")
    public Map TestUpload(@RequestPart(value = "photo_file", required = false) MultipartFile photo_file){
        return testFeignCon.TestUpload(photo_file);
    }


}