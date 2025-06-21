package cloud.hexiaolei.webaiproject.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 密钥 - 和测试类一致
    private static final String SECRET_KEY = Base64.getEncoder().encodeToString("hexiaolei".getBytes());

    // 过期时间 - 1小时
    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour in milliseconds

    /**
     * 生成 JWT Token
     *
     * @param claims 用户自定义负载信息，如用户ID、用户名等
     * @return 生成的 Token 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims) // 设置用户自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 设置加密算法和密钥
                .compact(); // 生成最终的 Token
    }

    /**
     * 解析 JWT Token
     *
     * @param token 待解析的 Token 字符串
     * @return Claims 对象，包含用户自定义数据
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 使用相同的密钥进行解析
                .parseClaimsJws(token)
                .getBody(); // 获取负载内容
    }
}
