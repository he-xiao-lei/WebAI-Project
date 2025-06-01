package cloud.hexiaolei.webaiproject.controller;

import cloud.hexiaolei.webaiproject.pojo.EmpJobOption;
import cloud.hexiaolei.webaiproject.pojo.Result;
import cloud.hexiaolei.webaiproject.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
