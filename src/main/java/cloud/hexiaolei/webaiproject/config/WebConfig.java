package cloud.hexiaolei.webaiproject.config;

import cloud.hexiaolei.webaiproject.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//配置类
@Configuration//加入Configuration注解，表示这是一个配置类,同时也会加入Component注解，加入IOC容器
public class WebConfig implements WebMvcConfigurer {
    private final TokenInterceptor tokenInterceptor;
    @Autowired
    public WebConfig(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
}
