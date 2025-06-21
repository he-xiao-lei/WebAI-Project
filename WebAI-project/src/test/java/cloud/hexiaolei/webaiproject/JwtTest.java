package cloud.hexiaolei.webaiproject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.*;

public class JwtTest {

    @Test
    public void generateToken() {
        //设置签名
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "jwt");
        header.put("alg", "HS256");
        //设置用户自定义数据
        Map<String, Object> data = new HashMap<>();
        data.put("id", 1);
        data.put("username", "hexiaolei");

        //生成token
        String token = Jwts.builder()
                .setHeader(header)//设置签名
                .setClaims(data)//设置用户自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 30))//设置过期时间
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString("hexiaolei".getBytes()))//设置加密算法和用户密钥
                .compact();//压缩生成最后的token

        System.out.println(token);//输出token
        String[] split = token.split("\\.");
        Arrays.stream(split).forEach(System.out::println);
    }

    /**
     * 解析jwt令牌
     */
    @Test
    public void parseToken() {
        String token = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzQ5MjEyNzMxLCJ1c2VybmFtZSI6ImhleGlhb2xlaSJ9.d6Xo7A7EOQThqfNN1H-pwnAkLKnJvDOyZbbxI7JQTNk";
        Claims body = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString("hexiaolei".getBytes()))
                .parseClaimsJws(token)
                .getBody();
        System.out.println(body);
    }
}
