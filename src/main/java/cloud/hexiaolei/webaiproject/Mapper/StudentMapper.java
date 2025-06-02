package cloud.hexiaolei.webaiproject.Mapper;

import cloud.hexiaolei.webaiproject.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> queryStudents();
}
