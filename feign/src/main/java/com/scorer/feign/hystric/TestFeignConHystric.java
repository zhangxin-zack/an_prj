package com.scorer.feign.hystric;

import com.scorer.feign.feign_con.TestFeignCon;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Component
public class TestFeignConHystric implements TestFeignCon {

    @Override
    public ResponseEntity<byte[]> TestNetSRC() {
        return null;
    }

    @Override
    public Map TestUpload(MultipartFile photo_file) {
        return null;
    }

}