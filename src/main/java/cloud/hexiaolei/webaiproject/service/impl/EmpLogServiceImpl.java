package cloud.hexiaolei.webaiproject.service.impl;

import cloud.hexiaolei.webaiproject.Mapper.EmpLogMapper;
import cloud.hexiaolei.webaiproject.pojo.InfoLog;
import cloud.hexiaolei.webaiproject.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    private final EmpLogMapper empLogMapper;

    @Autowired
    public EmpLogServiceImpl(EmpLogMapper empLogMapper){
        this.empLogMapper = empLogMapper;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)//需要在一个新的事务中运行
    public void insertLog(InfoLog infoLog) {
        empLogMapper.insert(infoLog);
    }
}
