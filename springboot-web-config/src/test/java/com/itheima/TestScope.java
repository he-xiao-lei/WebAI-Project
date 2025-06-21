package com.itheima;

import com.itheima.controller.DeptController;
import com.itheima.utils.AliyunOSSOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
@SpringBootTest
public class TestScope {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AliyunOSSOperator  aliyunOSSOperator;

    @Test
    public void testScope() {
        for (int i = 0; i < 100; i++) {
            Object deptController = applicationContext.getBean("deptController");
            System.out.println(deptController);
            //aslkdjflsadf8*/
            /*jaklsdfsdf

             */
        }
    }

    @Test
    public void upload()throws Exception{
//        aliyunOSSOperator.upload("jaskldf".getBytes(),"aaa");
        int m = 2;
        int n = 2;
        m+=m-=m*m;
        n-=n*n;
        n=n+n;
        System.out.println("m = " + m);
        System.out.println("n = " + n);
    }
}