package cloud.hexiaolei.webaiproject.service.impl;

import cloud.hexiaolei.webaiproject.Mapper.EmpExprMapper;
import cloud.hexiaolei.webaiproject.Mapper.EmpMapper;
import cloud.hexiaolei.webaiproject.pojo.*;
import cloud.hexiaolei.webaiproject.service.EmpLogService;
import cloud.hexiaolei.webaiproject.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 因为员工经历表是员工表的附属，所以最后还是通过一个EmpServiceImpl可以实现
 */
@Service
public class EmpServiceImpl implements EmpService {
    private final EmpMapper empMapper;
    private final EmpExprMapper empExprMapper;
    private final EmpLogService empLogService;
    @Autowired
    public EmpServiceImpl(EmpMapper empMapper,EmpExprMapper empExprMapper,EmpLogService empLogService){
        this.empMapper = empMapper;
        this.empExprMapper = empExprMapper;
        this.empLogService = empLogService;
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
    @Transactional(rollbackFor = {Exception.class})//此注解表示将当前方法加入spring事务管理，一个方法内有两次及以上的事件才需要启用事务管理
    //默认只抛出RuntimeException的异常，如果是Exception就不会回滚了
    public void insertUser(Emp emp) {
        try {
            //1.补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //2.添加用户
            empMapper.insertUser(emp);

        int i = 1/0;
            //在这边手动的构建一个异常，让程序执行到这里就会抛出异常，但是上面的已经执行了，下面的没有执行，所以现在就是需要一个解决方法，解决插入不对等的情况
            //业务操作不一致
            //3.工作经历添加,因为有可能是因为是实习生工作
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)){
                //遍历集合，给员工经历中的deptId赋值
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertEmpExpr(exprList);
            }
        } finally {//一定会执行
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            //记录操作日志
            empLogService.insertLog(empLog);
        }


    }

}
