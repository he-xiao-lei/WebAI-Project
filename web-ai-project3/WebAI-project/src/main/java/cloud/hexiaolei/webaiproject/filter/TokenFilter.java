package cloud.hexiaolei.webaiproject.filter;

import cloud.hexiaolei.webaiproject.utils.CurrentHolder;
import cloud.hexiaolei.webaiproject.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化");
    }

    /**
     * - 问题1：放行后访问对应资源，资源访问完成后，还会回到Filter中吗？ 会
     * - 问题2：如果回到Filter中，是重新执行还是执行放行后的逻辑呢？ 执行放行后逻辑
     * | 拦截路径     | urlPatterns 值 | 含义                             |
     * | ------------ | -------------- | -------------------------------- |
     * | 拦截具体路径 | /login         | 只有访问 /login 路径时，才会被拦截 |
     * | 目录拦截     | /emps/*        | 访问 /emps 下的所有资源，都会被拦截 |
     * | 拦截所有     | /*             | 访问所有资源，都会被拦截           |
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取请求路径
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        StringBuffer requestURL = request.getRequestURL();
        log.info("请求路径：{}", requestURL);

        //2.判断是否是登录请求,是否包含/login,如果是放行,不是就获取请求头中的token
        if (requestURL.toString().contains("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            log.info("登录请求，放行");
            return;
        }
        //3.获取请求头中的token,就是JWT
        String token = request.getHeader("token");
        //4.判断是否存在,如果不存在，返回未登录信息,响应401状态码
        if (token == null || token.isEmpty()) {//isEmpty判断是否为空,""
            log.info("令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401状态码，未认证
            return;
        }
        //5.如果存在，就调用jwt工具类，解析token
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = (Integer) claims.get("id");
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录员工id{},将其存入threadlocal", empId);
        } catch (Exception e) {
            log.info("令牌非法");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401状态码，未认证
            return;
        }
        //6.解析成功，放行，解析失败，返回未登录信息，响应401状态码
        log.info("令牌合法,放行");
        filterChain.doFilter(servletRequest, servletResponse);
        //7.删除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}

