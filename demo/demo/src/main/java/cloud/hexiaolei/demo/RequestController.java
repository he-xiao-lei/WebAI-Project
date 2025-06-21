package cloud.hexiaolei.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @RequestMapping("/request")
    public String request(HttpServletRequest http) {
        //请求方式
        System.out.println("请求方式：" + http.getMethod());
        //获取请求的url地址
        System.out.println("请求的url地址："+http.getRequestURL().toString());
        System.out.println("请求的uri地址："+http.getRequestURI());
        //获取请求协议
        System.out.println("请求协议："+http.getProtocol());
        //获取请求参数
        System.out.println("请求参数name："+http.getParameter("name"));
        System.out.println("请求参数age："+http.getParameter("age"));
        //获取请求头
        System.out.println("请求头："+http.getHeader("Accept"));
        return "OK";
    }
}
