package cloud.hexiaolei.webaiproject.service;

import cloud.hexiaolei.webaiproject.pojo.PageResult;
import cloud.hexiaolei.webaiproject.pojo.Student;
import cloud.hexiaolei.webaiproject.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> queryStudents(StudentQueryParam studentQueryParam);

    void deleteStudentsById(List<Integer> list);

    void addStudent(Student student);
}
