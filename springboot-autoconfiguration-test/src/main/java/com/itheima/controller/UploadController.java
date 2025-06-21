package com.itheima.controller;

import cloud.hexiaolei.aliyunossspringbootautoconfigure.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;


@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public String upload(MultipartFile image) throws Exception {
        //上传文件到阿里云 OSS
//        aliyunOSSOperator.upload("test".getBytes(StandardCharsets.UTF_8),"C:\\test");
        return null;
    }

}
