package cloud.hexiaolei.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController//表示当前类是一个请求处理类
public class MyController {
    @RequestMapping("/hello")
    public String hello(String str) {
        return "hello" + str;
    }

}
