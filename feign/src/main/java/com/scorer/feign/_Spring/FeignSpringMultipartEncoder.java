package com.scorer.feign._Spring;

import com.scorer.feign.tools.TestObject;
import feign.RequestTemplate;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

public class FeignSpringMultipartEncoder extends FormEncoder {

    public FeignSpringMultipartEncoder() {
        this(new Default());
    }

    public FeignSpringMultipartEncoder(Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor processor = (MultipartFormContentProcessor) getContentProcessor(ContentType.MULTIPART);
        processor.addWriter(new SpringSingleMultipartFileWriter());
//        processor.addWriter(new SpringManyMultipartFilesWriter());
        //使用新的封装类'FeignSpringManyMultipartFilesWriter'
        processor.addWriter(new FeignSpringManyMultipartFilesWriter());
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        // 单MultipartFile判断
        if (bodyType.equals(MultipartFile.class)) {
            MultipartFile file = (MultipartFile) object;
            Map data = Collections.singletonMap(file.getName(), object);
            super.encode(data, MAP_STRING_WILDCARD, template);
            return;
        } else if (bodyType.equals(MultipartFile[].class)) {
            // MultipartFile数组处理
            MultipartFile[] file = (MultipartFile[]) object;
            if (!TestObject.isEmpty(file)) {
                Map data = Collections.singletonMap("files", object);
                super.encode(data, MAP_STRING_WILDCARD, template);
                return;
            }
        }
        // 其他类型调用父类默认处理方法
        super.encode(object, bodyType, template);
    }

}