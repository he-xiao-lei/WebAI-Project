package cloud.hexiaolei.webaiproject.Mapper;

import cloud.hexiaolei.webaiproject.pojo.Student;
import cloud.hexiaolei.webaiproject.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> queryStudents(StudentQueryParam studentQueryParam);

    void deleteStudentsById(List<Integer> list);

    void addStudent(Student student);
}
