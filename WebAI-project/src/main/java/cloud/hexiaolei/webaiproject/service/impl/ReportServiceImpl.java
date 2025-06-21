package cloud.hexiaolei.webaiproject.service.impl;

import cloud.hexiaolei.webaiproject.Mapper.ReportMapper;
import cloud.hexiaolei.webaiproject.pojo.EmpJobOption;
import cloud.hexiaolei.webaiproject.pojo.StudentClazzOption;
import cloud.hexiaolei.webaiproject.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportMapper reportMapper;

    public ReportServiceImpl(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    @Override
    public EmpJobOption getEmpJobData() {
        //1.调用map接口，统计数据
        List<Map<String, Object>> list = reportMapper.countEmpJobData();//key:字段名,value:字段值
        List<String> list1 = list.stream().map(dataEmp -> String.valueOf(dataEmp.get("job_name"))).toList();
        List<Object> list2 = list.stream().map(dataEmp -> dataEmp.get("job_number")).toList();
        //2.拼装结果,返回

        return new EmpJobOption(list1,list2);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return reportMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
         return reportMapper.countStudentDegreeData();
    }

    @Override
    public StudentClazzOption getStudentClazzData() {
        List<Map<String, Object>> lists = reportMapper.countStudentClazzData();
        List<String>  clazzList = lists.stream().map(element -> String.valueOf(element.get("name"))).toList();
        List<Object> dataList = lists.stream().map(element -> element.get("value")).toList();
        return new StudentClazzOption(clazzList,dataList);
    }


}
