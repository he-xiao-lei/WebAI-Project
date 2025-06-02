package cloud.hexiaolei.webaiproject.service.impl;

import cloud.hexiaolei.webaiproject.Mapper.ClazzMapper;
import cloud.hexiaolei.webaiproject.pojo.Clazz;
import cloud.hexiaolei.webaiproject.pojo.ClazzQueryParam;
import cloud.hexiaolei.webaiproject.pojo.PageResult;
import cloud.hexiaolei.webaiproject.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    private final ClazzMapper clazzMapper;
    @Autowired
    private ClazzServiceImpl(ClazzMapper clazzMapper){
        this.clazzMapper = clazzMapper;
    }

    /**
     * 查询班级信息
     * @param clazzQueryParam 查询参数
     * @return 查询到的页
     */
    @Override
    public PageResult<Clazz> queryClazzPage(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());


        List<Clazz> clazzes = clazzMapper.queryClazzPage(clazzQueryParam);
        Page<Clazz> result = (Page<Clazz>) clazzes;
        return new PageResult<>(result.getTotal(),result);
    }

    @Override
    public void deleteClazzById(Integer id) {
        clazzMapper.deleteClazzById(id);
    }
}
