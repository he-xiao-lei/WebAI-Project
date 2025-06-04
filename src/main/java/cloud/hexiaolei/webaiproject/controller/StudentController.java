package cloud.hexiaolei.webaiproject.controller;

import cloud.hexiaolei.webaiproject.pojo.PageResult;
import cloud.hexiaolei.webaiproject.pojo.Result;
import cloud.hexiaolei.webaiproject.pojo.Student;
import cloud.hexiaolei.webaiproject.pojo.StudentQueryParam;
import cloud.hexiaolei.webaiproject.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping
    public Result queryStudents(StudentQueryParam studentQueryParam){
        PageResult<Student> studentPageResult = studentService.queryStudents(studentQueryParam);
        return Result.success(studentPageResult);
    }
    @DeleteMapping("/{ids}")
    public Result deleteUserById(@PathVariable(value = "ids") String id){
        String[] ids = id.split(",");
        List<Integer> list = Arrays.stream(ids).map(Integer::valueOf).toList();
        log.info("删除学员id:{}",list);
        studentService.deleteStudentsById(list);
        return Result.success();
    }
    @PostMapping
    public Result addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return Result.success();
    }
}
