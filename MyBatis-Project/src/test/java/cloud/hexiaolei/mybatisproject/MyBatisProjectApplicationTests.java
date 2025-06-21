package cloud.hexiaolei.mybatisproject;

import cloud.hexiaolei.mybatisproject.Mapper.UserMapper;
import cloud.hexiaolei.mybatisproject.pojo.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest//Springboot单元测试注解，加这个注解表示启动时会自动加载springboot，同时也会启动IOC容器
class MyBatisProjectApplicationTests {
    //先将容器内的MapperBean注入到本类中
    private final UserMapper userMapper;
    @Autowired
    public MyBatisProjectApplicationTests(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Test
    void UserMapperTest(){
        List<User> all = userMapper.findAll();
        all.forEach(System.out::println);
    }
    @Test
    void DeleteUserTest(){
        Integer i = userMapper.deleteById(1);
        System.out.println("影响的行数 = " + i);
        List<User> all = userMapper.findAll();
        all.forEach(System.out::println);
    }
    @Test
    void InsertUserTest(){
        User user = new User(null,"suiseise00", "123456", "hexiaolei", 18);
        userMapper.insertUser(user);
        List<User> all = userMapper.findAll();
        all.forEach(System.out::println);
    }


    /**
     *更新用户数据
     */
    @Test
    void UpdateUserTest(){
        User user = new User(2,"wuyanzu", "1aaaaa", "hexiaole", 10);
        userMapper.updateUser(user);
        List<User> all = userMapper.findAll();
        all.forEach(System.out::println);
    }
    /**
     * 根据username和password查询用户数据
     */
    @Test
    void findByUsernameAndPasswordTest(){
        User user = userMapper.findByUsernameAndPassword("wuyanzu", "1aaaaa");
        System.out.println("user = " + user);
    }
}
