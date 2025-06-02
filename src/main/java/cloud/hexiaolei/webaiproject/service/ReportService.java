package cloud.hexiaolei.webaiproject.service;

import cloud.hexiaolei.webaiproject.pojo.EmpJobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    EmpJobOption getEmpJobData();
    List<Map<String,Object>> getEmpGenderData();

}
