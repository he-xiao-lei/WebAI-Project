package cloud.hexiaolei.webaiproject.controller;

import cloud.hexiaolei.webaiproject.pojo.Emp;
import cloud.hexiaolei.webaiproject.pojo.LoginInfo;
import cloud.hexiaolei.webaiproject.pojo.Result;
import cloud.hexiaolei.webaiproject.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping()
public class LoginController {
    private final EmpService empService;
    @Autowired
    public LoginController(EmpService empService){
        this.empService = empService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录{}",emp);
        LoginInfo loginInfo=empService.login(emp);
        if (Objects.isNull(loginInfo)){
            return Result.error("账号或密码错误");
        }
        return Result.success(loginInfo);
    }


}
