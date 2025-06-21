package cloud.hexiaolei.webaiproject.service.impl;

import cloud.hexiaolei.webaiproject.Mapper.ClazzMapper;
import cloud.hexiaolei.webaiproject.Mapper.EmpLogMapper;
import cloud.hexiaolei.webaiproject.pojo.Clazz;
import cloud.hexiaolei.webaiproject.pojo.ClazzQueryParam;
import cloud.hexiaolei.webaiproject.pojo.InfoLog;
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
            if (LocalDate.now().isBefore(clazz.getBeginDate())){
                clazz.setStatus("未开课");
            }else if(LocalDate.now().isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            }else {
                clazz.setStatus("在读");
            }
            clazzMapper.insertClazz(clazz);
        } finally {
            InfoLog infoLog = new InfoLog(null,LocalDateTime.now(),"添加班级:"+clazz);
            empLogMapper.insert(infoLog);
        }
    }

    @Override
    public Clazz getClazzInfoById(Integer id) {
        return clazzMapper.getClazzInfoById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyClazz(Clazz clazz) {
        try {
            clazz.setUpdateTime(LocalDateTime.now());
            if (LocalDate.now().isBefore(clazz.getBeginDate())){
                   clazz.setStatus("未开课");
               } else if(LocalDate.now().isAfter(clazz.getEndDate())) {
                   clazz.setStatus("已结课");
               }else {
                   clazz.setStatus("在读");
               }
            clazzMapper.modifyClazz(clazz);
        } finally {
            InfoLog infoLog = new InfoLog(null,LocalDateTime.now(),"修改班级"+clazz);
            empLogMapper.insert(infoLog);
        }

    }

}
