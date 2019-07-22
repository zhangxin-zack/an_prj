package com.scorer.feign._Spring;

import feign.form.multipart.AbstractWriter;
import feign.form.multipart.Output;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;

public class FeignSpringManyMultipartFilesWriter extends AbstractWriter {

    //引用'FeignSpringSingleMultipartFileWriter'
    private final FeignSpringSingleMultipartFileWriter fileWriter = new FeignSpringSingleMultipartFileWriter();

    public FeignSpringManyMultipartFilesWriter() {
    }

    public void write(Output output, String boundary, String key, Object value) throws Exception {
        if (value instanceof MultipartFile[]) {
            MultipartFile[] files = (MultipartFile[]) value;
            for (MultipartFile file : files) {
                //转换为client可识别的名字
                this.fileWriter.write(output, boundary, file.getName(), file);
            }
        } else if (value instanceof Iterable) {
            Iterable iterable = (Iterable) value;
            for (Object file : iterable) {
                this.fileWriter.write(output, boundary, key, file);
            }
        }

    }

    public boolean isApplicable(Object value) {
        if (value == null) {
            return false;
        } else if (value instanceof MultipartFile[]) {
            return true;
        } else {
            if (value instanceof Iterable) {
                Iterable iterable = (Iterable) value;
                Iterator iterator = iterable.iterator();
                if (iterator.hasNext() && iterator.next() instanceof MultipartFile) {
                    return true;
                }
            }
            return false;
        }
    }
}