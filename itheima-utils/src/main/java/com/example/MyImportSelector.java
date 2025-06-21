package com.example;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.example.HeaderConfig","com.example.TokenParser"};//返回值是字符串数组，每一个元素都会被添加到IOC容器中
    }
}
