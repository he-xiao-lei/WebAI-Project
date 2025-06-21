package cloud.hexiaolei.aliyunossspringbootautoconfigure;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * aliyunOss自动配置类
 */
@Configuration
@EnableConfigurationProperties(AliyunOSSProperties.class)
public class AliyunOssAutoConfigure {
    //@PostConstruct 注解的方法会在 Bean 初始化完成后自动执行一次。
    @PostConstruct
    public void init() {
        System.out.println("====> 自动配置 AliyunOssAutoConfigure 被加载了！");
    }

    @Bean
    @ConditionalOnMissingBean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties aliyunOSSProperties) {
        return new AliyunOSSOperator(aliyunOSSProperties);
    }
}
