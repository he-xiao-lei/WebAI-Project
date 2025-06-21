import com.example.UserService;
import org.junit.jupiter.api.*;

@DisplayName("用户信息测试类")
public class UserService2Test {
    private UserService userService;
    @BeforeEach
    public void setUserService(){
        userService = new UserService();
    }

    @Test
    @DisplayName("测试-null")
    public void testGetGender1(){
        Assertions.assertThrows(IllegalArgumentException.class,()->userService.getGender(null));

    }
    @Test
    @DisplayName("测试-过短")
    public void testGender2(){
        Assertions.assertThrows(IllegalArgumentException.class,()->userService.getGender("100"));
    }
    @Test
    @DisplayName("测试-过长")
    public void testGender3(){
        Assertions.assertThrows(IllegalArgumentException.class,()->userService.getGender("1000000000000000000000000a"));
    }
    @Test
    @DisplayName("测试-正常男")
    public void testGender4(){
        Assertions.assertEquals("男",userService.getGender("320505200701261511"));
    }
    @Test
    @DisplayName("测试-正常女")
    public void testGender5(){
        Assertions.assertEquals("女",userService.getGender("320505200701261521"));

    }


}
