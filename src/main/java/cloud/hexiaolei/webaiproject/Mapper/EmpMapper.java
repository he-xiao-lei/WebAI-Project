package cloud.hexiaolei.webaiproject.Mapper;

import cloud.hexiaolei.webaiproject.pojo.Emp;
import cloud.hexiaolei.webaiproject.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 操作员工信息
 */
@Mapper
public interface EmpMapper {

//    List<Emp> queryPages(String name, Integer gender, LocalDate begin, LocalDate end);
        List<Emp> queryPages(EmpQueryParam empQueryParam);

        /**
         * 新增员工基本信息
         * @param emp
         */
        void insertUser(Emp emp);

        void deleteUserById(List<Integer> ids);



//-- 根据Id查找员工和员工工作经历
//        select e.*,
//        ee.id ee_id,
//        ee.emp_id ee_id,
//        ee.begin ee_begin,
//        ee.end ee_end,
//        ee.company ee_company,
//        ee.job ee_job
//        from emp e left join tlias.emp_expr ee on e.id = ee.emp_id where e.id = 22;
        Emp getInfo(Integer id);

        /**
         * 根据id修改员工基本信息
         * @param emp 每一个员工
         */
        void updateById(Emp emp);

        //统计员工职位人数
       @MapKey("jobName")
       List<Map<String, Object>> countEmpJobData();


       //统计员工性别
        List<Map<String,Object>> countEmpGenderData();
}
