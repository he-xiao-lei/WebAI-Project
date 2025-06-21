package cloud.hexiaolei.webaiproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
//设置扫描Servlet组件以启用filter过滤器
@ServletComponentScan
@SpringBootApplication
public class WebAiProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAiProjectApplication.class, args);
    }

}
