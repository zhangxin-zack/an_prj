package com.scorer.feign.feign_con;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@FeignClient(value = "scorer-client")
public interface TestFeignCon {

    @RequestMapping(value = "/Test/TestNetSRC")
    ResponseEntity<byte[]> TestNetSRC();

    @RequestMapping(value = "/Test/TestUpload",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map TestUpload(@RequestPart(value = "photo_file", required = false) MultipartFile photo_file);

}
