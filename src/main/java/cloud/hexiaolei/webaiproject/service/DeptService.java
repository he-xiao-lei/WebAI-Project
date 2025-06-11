package cloud.hexiaolei.webaiproject.service;

import cloud.hexiaolei.webaiproject.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteDeptById(Integer id);

    void insertUser(Dept dept);

    Dept findUserById(Integer id);

    void updateUser(Dept dept);
}
