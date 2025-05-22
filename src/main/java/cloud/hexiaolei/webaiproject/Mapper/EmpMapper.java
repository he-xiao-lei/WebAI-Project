package cloud.hexiaolei.webaiproject.Mapper;
import cloud.hexiaolei.webaiproject.pojo.Emp;
import cloud.hexiaolei.webaiproject.pojo.EmpExpr;
import cloud.hexiaolei.webaiproject.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

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
}
