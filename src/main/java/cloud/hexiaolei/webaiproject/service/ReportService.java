package cloud.hexiaolei.webaiproject.service;

import cloud.hexiaolei.webaiproject.pojo.EmpJobOption;
import cloud.hexiaolei.webaiproject.pojo.StudentClazzOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    EmpJobOption getEmpJobData();
    List<Map<String,Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

   StudentClazzOption getStudentClazzData();
}
