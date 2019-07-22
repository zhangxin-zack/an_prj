package com.scorer.feign._Spring;

import feign.form.multipart.Output;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.web.multipart.MultipartFile;

public class FeignSpringSingleMultipartFileWriter extends SpringSingleMultipartFileWriter {

    public FeignSpringSingleMultipartFileWriter() {
    }

    @Override
    protected void write(Output output, String key, Object value) throws Exception {
        MultipartFile file = (MultipartFile) value;

        //file.getName()就是文件区分标识
        this.writeFileMetadata(output, key, file.getName() + "." + file.getOriginalFilename(), file.getContentType());

        output.write(file.getBytes());
    }
}