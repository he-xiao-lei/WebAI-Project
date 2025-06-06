package cloud.hexiaolei.webaiproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    //初始化方法，在web服务器启动前执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化");
    }
    //拦截到请求之后，执行，执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到请求{}",servletRequest.toString());
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
    //销毁方法，在web服务器关闭时执行，只执行一次
    @Override
    public void destroy() {
        log.info("销毁");
    }
}
