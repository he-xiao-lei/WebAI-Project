package cloud.hexiaolei.webaiproject.Mapper;

import cloud.hexiaolei.webaiproject.pojo.Clazz;
import cloud.hexiaolei.webaiproject.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> queryClazzPage(ClazzQueryParam clazzQueryParam);

    void deleteClazzById(Integer id);

    void insertClazz(Clazz clazz);

    Clazz getClazzInfoById(Integer id);

    void modifyClazz(Clazz clazz);
}
