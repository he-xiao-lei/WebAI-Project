package cloud.hexiaolei.mybatisproject.Mapper;

import cloud.hexiaolei.mybatisproject.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//加入这个接口表示在运行时，会自动地生成代理对象，代理对象会实现这个接口，并且自动将创建的实现类对象加入IOC容器中
public interface UserMapper {
    /**
   * 查询所有
   */
//    @Select("select * from user;")
    List<User> findAll();

    /**
     * 根据用户id删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id};")
    //这里的返回值是影响到的行数
    Integer deleteById(Integer id);
    /**
     * 新增用户
     */
    @Insert("insert into user(username,password,name,age) value (#{username},#{password},#{name},#{age})")
    void insertUser(User user);
    /**
     *  根据ID更新用户数据
     */
    @Update("update user set username=#{username},password=#{password},name=#{name},age=#{age} where id=#{id}")
    void updateUser(User user);
    /**
     * 根据username和password查询用户
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    //@Param注解表示参数的名称，这个注解是可选的，如果没有这个注解，参数名就是username和password
    User findByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
}
