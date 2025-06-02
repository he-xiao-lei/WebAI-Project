package cloud.hexiaolei.webaiproject.service.impl;

import cloud.hexiaolei.webaiproject.Mapper.ClazzMapper;
import cloud.hexiaolei.webaiproject.Mapper.EmpLogMapper;
import cloud.hexiaolei.webaiproject.pojo.Clazz;
import cloud.hexiaolei.webaiproject.pojo.ClazzQueryParam;
import cloud.hexiaolei.webaiproject.pojo.EmpLog;
import cloud.hexiaolei.webaiproject.pojo.PageResult;
import cloud.hexiaolei.webaiproject.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    private final ClazzMapper clazzMapper;
    private final EmpLogMapper empLogMapper;

    @Autowired
    public ClazzServiceImpl(ClazzMapper clazzMapper, EmpLogMapper empLogMapper) {
        this.clazzMapper = clazzMapper;
        this.empLogMapper = empLogMapper;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertClazz(Clazz clazz) {
        try {
            clazz.setCreateTime(LocalDateTime.now());
            clazz.setUpdateTime(LocalDateTime.now());
            if (clazz.getBeginDate().isBefore(LocalDate.now())){
                clazz.setStatus("已开课");
            }else {
                clazz.setStatus("未开课");
            }
            clazzMapper.insertClazz(clazz);
        } finally {
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"添加班级:"+clazz);
            empLogMapper.insert(empLog);
        }
    }

    @Override
    public Clazz getClazzInfoById(Integer id) {
        return clazzMapper.getClazzInfoById(id);
    }

    @Override
    public void modifyClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        if (clazz.getBeginDate().isBefore(LocalDate.now())){
            clazz.setStatus("已开课");
        }else {
            clazz.setStatus("未开课");
        }
        clazzMapper.modifyClazz(clazz);

    }

}
