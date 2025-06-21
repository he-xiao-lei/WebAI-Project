package cloud.hexiaolei.webaiproject.service;


import cloud.hexiaolei.webaiproject.pojo.Emp;
import cloud.hexiaolei.webaiproject.pojo.EmpQueryParam;
import cloud.hexiaolei.webaiproject.pojo.LoginInfo;
import cloud.hexiaolei.webaiproject.pojo.PageResult;

import java.util.List;


public interface EmpService{

    PageResult<Emp> queryPages(EmpQueryParam empQueryParam);
    void insertUser(Emp emp);

    void deleteUserById(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> getAllEmp();
//员工登录方法
    LoginInfo login(Emp emp);
}
