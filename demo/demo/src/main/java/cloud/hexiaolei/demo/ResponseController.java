package cloud.hexiaolei.demo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOError;
import java.io.IOException;

@RestController
public class ResponseController {


    /**
     * 方式1: 通过HttpServletResponse设置响应信息
     * @param response
     * @throws IOException
     */

    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        //设置响应状态码
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        //设置响应头
        response.setHeader("Content-Type","text/html;charset=utf-8");
        //设置响应体
        response.getWriter().write("<h1>Fuck you</h1>");
    }

    /**
     * 方式2: 通过ResponseEntity设置响应信息
     * @return
     */
    @RequestMapping("/response2")
    public ResponseEntity<String> response2(){
        return ResponseEntity.status(404).header("name","javaweb").body("<h1>Fuck you</h1>");
    }
}
