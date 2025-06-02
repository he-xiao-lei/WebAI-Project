package cloud.hexiaolei.webaiproject.service;

import cloud.hexiaolei.webaiproject.pojo.PageResult;
import cloud.hexiaolei.webaiproject.pojo.Student;
import cloud.hexiaolei.webaiproject.pojo.StudentQueryParam;

public interface StudentService {
    PageResult<Student> queryStudents(StudentQueryParam studentQueryParam);
}
