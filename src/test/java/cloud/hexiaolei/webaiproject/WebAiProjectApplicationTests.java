package cloud.hexiaolei.webaiproject;

import ch.qos.logback.classic.Logger;
import cloud.hexiaolei.webaiproject.Mapper.DeptMapper;
import cloud.hexiaolei.webaiproject.pojo.Dept;
import cloud.hexiaolei.webaiproject.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebAiProjectApplicationTests {
    private final DeptService deptService;

    @Autowired
    public WebAiProjectApplicationTests(DeptService deptService  ) {
        this.deptService = deptService;
    }



    @Test
    void test(){
        List<Dept> deptList = deptService.findAll();
        for (Dept dept : deptList) {
            System.out.println(dept);
        }
    }

}
