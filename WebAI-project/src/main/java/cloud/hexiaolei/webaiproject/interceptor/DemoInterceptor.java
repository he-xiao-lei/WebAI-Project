package cloud.hexiaolei.webaiproject.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Component//因为这个类是拦截器，所以要交给spring容器管理，所以要加@Component注解
public class DemoInterceptor implements HandlerInterceptor {
    @Override//目标资源方法执行前执行，返回true，放行，返回false，拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      log.info("prehandler运行了");
      return true;
    }

    @Override//目标资源方法执行之后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("posthandle...");
    }
    @Override//视图渲染完毕以后执行，不常用
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("视图渲染完毕");
    }
}
