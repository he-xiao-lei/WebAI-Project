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
}
