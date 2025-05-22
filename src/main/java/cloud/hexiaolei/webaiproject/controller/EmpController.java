package cloud.hexiaolei.webaiproject.controller;

import cloud.hexiaolei.webaiproject.pojo.*;
import cloud.hexiaolei.webaiproject.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController //员工管理controller
@RequestMapping("/emps")
public class EmpController {
    private final EmpService empService;
    @Autowired
    public EmpController(EmpService empService){
        this.empService = empService;
    }
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        /*
          原来public Result page(
                      @RequestParam String name,
         *             @RequestParam Integer gender,
         *             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,//设置前端传入格式
         *             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,//设置前端传入格式
         *             @RequestParam(defaultValue = "1") Integer page,
         *             @RequestParam(defaultValue = "10") Integer pageSize
         *             ){
         *         log.info("分页查询第{}页,每页{}个员工,姓名{},性别{},开始时间{},结束时间{}",page,pageSize,name,gender,begin,end);
         *         PageResult<Emp> pageResult = empService.queryPages(page, pageSize,name,gender,begin,end);
         *         return Result.success(pageResult);
         *     }

         */
//        log.info("分页查询第{}页,每页{}个员工,姓名{},性别{},开始时间{},结束时间{}",page,pageSize,name,gender,begin,end);
        log.info("参数{}",empQueryParam);
        PageResult<Emp> pageResult = empService.queryPages(empQueryParam);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        //1.保存员工基本信息
        log.info("添加员工{}",emp);
        empService.insertUser(emp);
        //2.保存员工工作经历
        return Result.success();
    }
}
