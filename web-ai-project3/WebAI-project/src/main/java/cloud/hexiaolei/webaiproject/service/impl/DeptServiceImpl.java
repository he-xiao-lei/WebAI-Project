package cloud.hexiaolei.webaiproject.service.impl;

import cloud.hexiaolei.webaiproject.Mapper.DeptMapper;
import cloud.hexiaolei.webaiproject.pojo.Dept;
import cloud.hexiaolei.webaiproject.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    private final DeptMapper deptMapper;

    @Autowired
    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteDeptById(Integer id) {
        deptMapper.deleteDeptById(id);
    }

    @Override
    public void insertUser(Dept dept) {
        //补全基础属性,createTime,updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //调用map接口插入数据
        deptMapper.insertDept(dept);
    }

    @Override
    public Dept findUserById(Integer id) {
        return deptMapper.findDeptById(id);
    }

    @Override
    public void updateUser(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDeptById(dept);
    }


}
