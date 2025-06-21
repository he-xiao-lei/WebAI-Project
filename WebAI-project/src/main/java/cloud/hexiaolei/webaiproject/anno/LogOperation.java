package cloud.hexiaolei.webaiproject.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//表示这个注解只能加在方法上
@Retention(RetentionPolicy.RUNTIME)//表示运行时生效
//起到标识的作用
public @interface LogOperation {

}
