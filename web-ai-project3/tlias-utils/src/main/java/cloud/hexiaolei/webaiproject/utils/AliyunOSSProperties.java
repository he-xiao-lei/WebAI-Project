package cloud.hexiaolei.webaiproject.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data//是Lombok中的注解用于标准javabean方法的生成
@Component//加入IOC容器管理
@ConfigurationProperties(prefix = "aliyun.oss")//配置属性,设置前缀
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}
