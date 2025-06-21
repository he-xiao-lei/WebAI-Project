package cloud.hexiaolei.demo.Service.impl;

import cloud.hexiaolei.demo.Dao.UserDao;
import cloud.hexiaolei.demo.Dao.impl.UserDaoimpl;
import cloud.hexiaolei.demo.Service.UserService;
import cloud.hexiaolei.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
//@Component
public class UserServiceimpl implements UserService {
    //1.调用DAO获取数据
    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        List<User> collect = userDao.findAll().stream().map(x -> {
            String[] parts = x.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime date = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, date);
        }).toList();
        return collect;
    }
}
