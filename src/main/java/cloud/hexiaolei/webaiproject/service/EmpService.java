package cloud.hexiaolei.webaiproject.service;


import cloud.hexiaolei.webaiproject.pojo.Emp;
import cloud.hexiaolei.webaiproject.pojo.EmpExpr;
import cloud.hexiaolei.webaiproject.pojo.EmpQueryParam;
import cloud.hexiaolei.webaiproject.pojo.PageResult;


public interface EmpService{

    PageResult<Emp> queryPages(EmpQueryParam empQueryParam);
    void insertUser(Emp emp);
}
