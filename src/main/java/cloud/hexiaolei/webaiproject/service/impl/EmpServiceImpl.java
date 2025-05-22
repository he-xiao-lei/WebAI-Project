package cloud.hexiaolei.webaiproject.service.impl;

import cloud.hexiaolei.webaiproject.Mapper.EmpExprMapper;
import cloud.hexiaolei.webaiproject.Mapper.EmpMapper;
import cloud.hexiaolei.webaiproject.pojo.Emp;
import cloud.hexiaolei.webaiproject.pojo.EmpExpr;
import cloud.hexiaolei.webaiproject.pojo.EmpQueryParam;
import cloud.hexiaolei.webaiproject.pojo.PageResult;
import cloud.hexiaolei.webaiproject.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 因为员工经历表是员工表的附属，所以最后还是通过一个EmpServiceImpl可以实现
 */
@Service
public class EmpServiceImpl implements EmpService {
    private final EmpMapper empMapper;
    private final EmpExprMapper empExprMapper;
    @Autowired
    public EmpServiceImpl(EmpMapper empMapper,EmpExprMapper empExprMapper){
        this.empMapper = empMapper;
        this.empExprMapper = empExprMapper;
    }

//原始方式
//    @Override
//    public PageResult<Emp> queryPages(Integer page,Integer pageSize) {
//        List<Emp> emps = empMapper.queryPages((page - 1) * pageSize,pageSize);
//        PageResult<Emp> pageResult = new PageResult<>(count(),emps);
////        pageResult.setRow(emps);
////        pageResult.setTotal(count());
//        return pageResult;
//    }
@Override
public PageResult<Emp> queryPages(EmpQueryParam empQueryParam) {
   //1. 设置需要查询的页码和每页的个数
   PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
   //2. 调用方法进行查询
   List<Emp> emps = empMapper.queryPages(empQueryParam);

    //3. 返回结果
   Page<Emp> empPage = (Page<Emp>) emps;
   return new PageResult<>(empPage.getTotal(),emps);

}

    @Override
    public void insertUser(Emp emp) {
        //1.补全基础属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //2.添加用户
        empMapper.insertUser(emp);
        //3.工作经历添加(可没有)
        if (!CollectionUtils.isEmpty(emp.getExprList())){
            empExprMapper.insertEmpExpr(emp.getExprList());
        }
    }

}
