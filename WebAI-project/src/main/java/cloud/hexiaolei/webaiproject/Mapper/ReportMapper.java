package cloud.hexiaolei.webaiproject.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    List<Map<String,Object>> countEmpGenderData();

    List<Map<String,Object>> countEmpJobData();

    List<Map<String,Object>> countStudentDegreeData();

    List<Map<String,Object>> countStudentClazzData();
}
