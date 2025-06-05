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

    /**
     * 添加学员
     * @param student 添加的每一个学生
     * @return 成功
     */
    @PostMapping
    public Result addStudent(@RequestBody Student student){
        log.info("添加员工信息:{}",student);
        studentService.addStudent(student);
        return Result.success();
    }

    /**
     * 根据id查询员工
     * @param id 员工id
     * @return 员工信息
     */
    @GetMapping("/{id}")
    public Result queryStudentById(@PathVariable(value = "id") Integer id){
        log.info("查询员工id为{}的员工信息",id);
        Student student = studentService.queryStudentById(id);
        return Result.success(student);
    }
    @PutMapping
    public Result updateStudent(@RequestBody Student student){
        log.info("更新员工信息:{}",student);
        studentService.updateStudent(student);
        return Result.success();
    }
    @PutMapping("/violation/{id}/{score}")
    public Result updateStudentViolation(@PathVariable(value = "id") Integer id,@PathVariable(value = "score") Integer score){
        log.info(" 学生id{}扣{}分",id,score);
        studentService.updateStudentViolation(id,score);
        return Result.success();
    }

}
