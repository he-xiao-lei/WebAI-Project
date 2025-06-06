package cloud.hexiaolei.webaiproject.filter;

import cloud.hexiaolei.webaiproject.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取请求路径
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        StringBuffer requestURL = request.getRequestURL();
        log.info("请求路径：{}",requestURL);

        //2.判断是否是登录请求,是否包含/login,如果是放行,不是就获取请求头中的token
        if (requestURL.toString().contains("/login")){
            filterChain.doFilter(servletRequest,servletResponse);
            log.info("登录请求，放行");
            return;
        }
        //3.获取请求头中的token,就是JWT
        String token = request.getHeader("token");
        //4.判断是否存在,如果不存在，返回未登录信息,响应401状态码
        if (token == null || token.isEmpty()){//isEmpty判断是否为空,""
            log.info("令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401状态码，未认证
            return;
        }
        //5.如果存在，就调用jwt工具类，解析token
        try {
            JwtUtils.parseToken(token);
        }catch (Exception e){
            log.info("令牌非法");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401状态码，未认证
            return;
        }
        //6.解析成功，放行，解析失败，返回未登录信息，响应401状态码
        log.info("令牌合法,放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("销毁");
    }
}
