package cloud.hexiaolei.webaiproject.controller;

import cloud.hexiaolei.webaiproject.pojo.Clazz;
import cloud.hexiaolei.webaiproject.pojo.ClazzQueryParam;
import cloud.hexiaolei.webaiproject.pojo.PageResult;
import cloud.hexiaolei.webaiproject.pojo.Result;
import cloud.hexiaolei.webaiproject.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    private final ClazzService clazzService;
    @Autowired
    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    /**
     * 查询班级
     * @param clazzQueryParam 查询参数
     * @return 查询的班级
     */
    @GetMapping
    public Result queryClass(ClazzQueryParam clazzQueryParam){
        PageResult<Clazz> clazzPageResult = clazzService.queryClazzPage(clazzQueryParam);
        return Result.success(clazzPageResult);
    }
    @DeleteMapping("/{id}")
    public Result deleteClazzById(@PathVariable(value = "id")Integer id){
        clazzService.deleteClazzById(id);
        return Result.success();
    }

    @PostMapping
    public Result insertClazz(@RequestBody Clazz clazz){
        clazzService.insertClazz(clazz);
        return Result.success();
    }
    /**
     * id
     * 回显功能，修改的前置步骤
     * 3.4 根据ID查询
     * 3.4.1 基本信息
     * 请求路径：/clazzs/{id}
     * 请求方式：GET
     * 接口描述：该接口用于根据主键ID查询班级的信息
     */
    @GetMapping("/{id}")
    public Result getClazzInfo(@PathVariable(value = "id")Integer id){
        Clazz clazzInfoById = clazzService.getClazzInfoById(id);
        return Result.success(clazzInfoById);
    }
    /**
     * 3.5 修改班级
     * 3.5.1 基本信息
     * 请求路径：/clazzs
     * 请求方式：PUT
     * 接口描述：该接口用于修改班级的数据信息
     */
    @PutMapping
    public Result modifyClazz(@RequestBody Clazz clazz){
        clazzService.modifyClazz(clazz);
        return Result.success();
    }
}
