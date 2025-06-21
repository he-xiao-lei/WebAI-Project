package com.itheima;

import cn.hutool.http.Header;
import com.example.HeaderConfig;
import com.example.HeaderParser;
import com.example.TokenParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUtil {


//    @Autowired
//    private TokenParser tokenParser;
//
//    @Test
//    public void testUtils(){
//        tokenParser.parse();
//    }
    @Autowired
    private HeaderParser headerParser;
    @Test
    public void testUtils(){
        headerParser.parse();
    }

}
