import com.example.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
@DisplayName("用户信息测试类")
public class UserServiceTest {
//    @BeforeAll//在所有单元测试方法运行之前，运行一次
//    public static void beforeAll(){
//        System.out.println("beforeall");
//    }
//    @AfterAll//在所有单元测试方法运行之后，运行一次
//    public static void afterAll(){
//        System.out.println("afterall");
//    }
//    @BeforeEach//在每个单元测试方法运行之前，都运行一次
//    public void beforeeach(){
//        System.out.println("beforEach");
//    }
//    @AfterEach//在每个单元测试方法运行之前，都运行一次
//    public void afterEach(){
//        System.out.println("afterEach");
//    }


    @Test
    public void testGetAge(){
        int age = new UserService().getAge("320505200701261511");
        System.out.println("age = " + age);
    }
    @Test
    public  void testGetGender(){
        String gender = new UserService().getGender("320505200701261511");
        System.out.println(gender);
    }


    /*
    基于断言的方式:是否符合预期
     */
    @Test
    public void testGetGenderWithAssert(){
        String gender = new UserService().getGender("320505200701261511");
        System.out.println(gender);

        Assertions.assertEquals("男",gender,"错误");



    }

    @Test
    public void testGetGenderWithAssert2(){
        UserService userService= new UserService();
        //断言
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            userService.getGender("32050520070126");
        });
    }

    /**
     * 参数化测试
     */
    @DisplayName("测试用户性别")
    @ParameterizedTest
    @ValueSource(strings = {"320505200701261511","320505200701251511"})
    public void testGetGender2(String idcard){
        UserService us = new UserService();

        Assertions.assertEquals("男",us.getGender(idcard),"性别获取逻辑错误");



    }
}
