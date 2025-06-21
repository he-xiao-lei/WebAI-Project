package cloud.hexiaolei.webaiproject.controller;

import cloud.hexiaolei.webaiproject.pojo.EmpJobOption;
import cloud.hexiaolei.webaiproject.pojo.Result;
import cloud.hexiaolei.webaiproject.pojo.StudentClazzOption;
import cloud.hexiaolei.webaiproject.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService){
        this.reportService= reportService;

    }

    /**
     * 统计员工职位人数
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("返回员工职位人数");
        EmpJobOption empJobData = reportService.getEmpJobData();
        return Result.success(empJobData);
    }
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("返回员工性别统计");
        List<Map<String, Object>> empGenderData = reportService.getEmpGenderData();
        return Result.success(empGenderData);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudnetDegreeData(){
        List<Map<String,  Object>> result = reportService.getStudentDegreeData();
        return Result.success(result);
    }
    @GetMapping("/studentCountData")
    public Result getStudentGender(){
        StudentClazzOption studentClazzData = reportService.getStudentClazzData();
        return Result.success(studentClazzData);

    }
}
