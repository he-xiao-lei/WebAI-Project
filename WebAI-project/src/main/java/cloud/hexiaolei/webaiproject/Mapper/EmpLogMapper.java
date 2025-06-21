package cloud.hexiaolei.webaiproject.Mapper;

import cloud.hexiaolei.webaiproject.pojo.InfoLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpLogMapper {

    @Insert("insert into Info_log (operate_time, info) values (#{operateTime}, #{info})")
    public void insert(InfoLog infoLog);

}
