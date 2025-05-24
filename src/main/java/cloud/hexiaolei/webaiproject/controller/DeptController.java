package cloud.hexiaolei.webaiproject.controller;

import cloud.hexiaolei.webaiproject.pojo.Dept;
import cloud.hexiaolei.webaiproject.pojo.Result;
import cloud.hexiaolei.webaiproject.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    private final DeptService deptService;
    @Autowired
    public DeptController(DeptService deptService){
        this.deptService = deptService;
    }

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)//指定请求方式
//    可以直接使用@GetMapping注解
    @GetMapping
    public Result getDept(){
        log.warn("查询所有记录");
        List<Dept> all = deptService.findAll();
        return Result.success(all);
    }

    @DeleteMapping
    public Result deleteUserById(@RequestParam(value = "id") Integer id) {
        log.warn("根据id:{}删除部门", id);
        deptService.deleteUserById(id);
        return Result.success();
    }
    @PostMapping
    public Result insertUser(@RequestBody Dept dept) {
//        System.out.println("新增部门"+dept);
        log.warn("新增部门:{}", dept.getName());
        deptService.insertUser(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findUserById(@PathVariable(value = "id") Integer id) {
//        System.out.println("根据部门id查询部门");
        log.warn("根据部门id:{}查询", id);
        Dept dept = deptService.findUserById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result updateUserById(@RequestBody Dept dept) {
        log.warn("修改部门:{}", dept);
        deptService.updateUser(dept);
        return Result.success();
    }



}
