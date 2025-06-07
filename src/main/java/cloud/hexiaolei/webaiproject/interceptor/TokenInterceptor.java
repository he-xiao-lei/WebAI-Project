package cloud.hexiaolei.webaiproject.interceptor;

import cloud.hexiaolei.webaiproject.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {//需要实现handlerInterceptor接口
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("请求URI：{}",requestURI);
        if (requestURI.contains("/login")){
            log.info("登录请求,放行");
            return true;
        }
        String token = request.getHeader("token");

        if (token == null || token.isEmpty()){
            log.info("令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401状态码，未认证
            return false;
        }
        try {
            JwtUtils.parseToken(token);
        }catch (Exception e){
            log.info("令牌非法或过期");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401状态码，未认证
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("获取资源后");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

