package cloud.hexiaolei.webaiproject.config;

import cloud.hexiaolei.webaiproject.interceptor.TokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置类
@Configuration//加入Configuration注解，表示这是一个配置类,同时也会加入Component注解，加入IOC容器
public class WebConfig implements WebMvcConfigurer {
    /**
     * | 拦截路径     | 含义                  | 举例                                                         |
     * | ------------ | --------------------- | ------------------------------------------------------------ |
     * | `/*`         | 一级路径              | 能匹配`/depts`，`/emps`，`/login`，不能匹配`/depts/1`         |
     * | `/**`        | 任意级路径            | 能匹配`/depts`，`/depts/1`，`/depts/1/2`                     |
     * | `/depts/*`   | `/depts`下的一级路径  | 能匹配`/depts/1`，不能匹配`/depts/1/2`，`/depts`             |
     * | `/depts/**`  | `/depts`下的任意级路径| 能匹配`/depts`，`/depts/1`，`/depts/1/2`，不能匹配`/emps/1`  |
     */
//    @Autowired
//    private DemoInterceptor demoInterceptor;
    private final TokenInterceptor tokenInterceptor;

    @Autowired
    public WebConfig(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    //添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns("/**");//设置拦截路径的同时可以设置不要拦截的路径
    }
}

// 路过前端不配置代理，后端就需要配置CORS
//    @Override
//    //配置跨域访问
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Allow CORS for all endpoints
//                .allowedOrigins("http://localhost:5173") // Allow requests from frontend origin
//                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
//                .allowedHeaders("*") // Allow all headers
//                .allowCredentials(true); // Allow credentials (e.g., cookies, authorization headers)
//    }
//}
