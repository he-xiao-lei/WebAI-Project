package cloud.hexiaolei.webaiproject.service.impl;

import cloud.hexiaolei.webaiproject.Mapper.StudentMapper;
import cloud.hexiaolei.webaiproject.pojo.PageResult;
import cloud.hexiaolei.webaiproject.pojo.Student;
import cloud.hexiaolei.webaiproject.pojo.StudentQueryParam;
import cloud.hexiaolei.webaiproject.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }
    @Override
    public PageResult<Student> queryStudents(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());

        List<Student> students = studentMapper.queryStudents(studentQueryParam);

        Page<Student> studentsList = (Page<Student>) students;


        return new PageResult<>(studentsList.getTotal(),studentsList);

    }
}
