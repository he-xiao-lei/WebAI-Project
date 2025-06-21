package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import(cloud.hexiaolei.aliyunossspringbootautoconfigure.AliyunOssAutoConfigure.class)

public class SpringbootAutoconfigurationTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAutoconfigurationTestApplication.class, args);
    }

}
