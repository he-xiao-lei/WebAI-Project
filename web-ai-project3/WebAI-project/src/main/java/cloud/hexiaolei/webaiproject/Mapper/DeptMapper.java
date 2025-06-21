package cloud.hexiaolei.webaiproject.Mapper;

import cloud.hexiaolei.webaiproject.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
/**
 * 方式1:手动封装
 */
//@Results(
//        {
//                @Result(column = "update_time",property = "updateTime"),
//                @Result(column = "create_time",property = "createTime")
//        }
//)
    /**
     * 查找部门
     * @return
     */
    //方式2:起别名
    //j@Select("select id, name, create_time, update_time from dept order by update_time desc;")
    List<Dept> findAll();

    /**
     * 删除部门
     * @param id
     */
    void deleteDeptById(@Param(value = "id") Integer id);
    /**
     * 新增部门
     */
    void insertDept(Dept dept);

    /**
     * 查询某一个部门
     */
    Dept findDeptById(@Param(value = "id")Integer id);


    /**
     * 修改部门
     */
    void updateDeptById(Dept dept);



}
