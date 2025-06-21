package cloud.hexiaolei.demo.Controller;

import cloud.hexiaolei.demo.Service.UserService;
import cloud.hexiaolei.demo.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;


/**
 * 用户信息的controller
 * 请求处理类
 */
@RestController

public class UserController {//@ResponseBody->作用：直接将controller返回值作为响应体的数据直接响应，返回值是对象/集合-> JSON ->响应体
    //1.方式1
//    @Qualifier("userServiceimpl2")
//    @Autowired//应用程序运行时，会自动查找该类型的Bean对象，并赋值给当前成员变量
//    private UserService userService;
    //or
    @Resource(name = "userServiceimpl2")
    private UserService userService;
    //2.方式2
//    private final UserService userService;
//    @Autowired //->如果当前类中只存在一个构造函数@Autowired可以省略
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
    //3.方式3
//    private UserService userService;
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @RequestMapping("/list")
    public List<User> list() throws FileNotFoundException {
        return userService.findAll();
    }
}
