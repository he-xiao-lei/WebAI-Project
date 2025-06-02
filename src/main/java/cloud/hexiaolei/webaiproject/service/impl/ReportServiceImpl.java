package cloud.hexiaolei.webaiproject.service.impl;

import cloud.hexiaolei.webaiproject.Mapper.EmpMapper;
import cloud.hexiaolei.webaiproject.pojo.EmpJobOption;
import cloud.hexiaolei.webaiproject.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    private final EmpMapper empMapper;

    public ReportServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    @Override
    public EmpJobOption getEmpJobData() {
        //1.调用map接口，统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();//key:字段名,value:字段值
        List<String> list1 = list.stream().map(dataEmp -> String.valueOf(dataEmp.get("job_name"))).toList();
        List<Object> list2 = list.stream().map(dataEmp -> dataEmp.get("job_number")).toList();
        //2.拼装结果,返回

        return new EmpJobOption(list1,list2);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }



}
