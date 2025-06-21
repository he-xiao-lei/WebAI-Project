package com.itheima;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.itheima.pojo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DITest {

    @Autowired
    private Gson gson;

    @Test
    public void test(){
        String json = gson.toJson(Result.success());
        System.out.println(json);

    }

}
