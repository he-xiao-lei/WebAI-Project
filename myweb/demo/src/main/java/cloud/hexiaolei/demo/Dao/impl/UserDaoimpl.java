package cloud.hexiaolei.demo.Dao.impl;

import cloud.hexiaolei.demo.Dao.UserDao;
import cn.hutool.core.io.IoUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Repository//("userDao")
//@Component//将当前类给IOC容器管理
public class UserDaoimpl implements UserDao {
    @Override
    public List<String> findAll() {
//1.加载并读取user.txt文件内容
//        InputStream is = new FileInputStream("src\\main\\resources\\user.txt");
        //利用类加载器找到资源文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> objects = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return objects;
    }
}
