package cloud.hexiaolei.webaiproject.service;

import cloud.hexiaolei.webaiproject.pojo.Clazz;
import cloud.hexiaolei.webaiproject.pojo.ClazzQueryParam;
import cloud.hexiaolei.webaiproject.pojo.PageResult;

public interface ClazzService {
    PageResult<Clazz> queryClazzPage(ClazzQueryParam clazzQueryParam);

    void deleteClazzById(Integer id);

    void insertClazz(Clazz clazz);

    Clazz getClazzInfoById(Integer id);

    void modifyClazz(Clazz clazz);
}
