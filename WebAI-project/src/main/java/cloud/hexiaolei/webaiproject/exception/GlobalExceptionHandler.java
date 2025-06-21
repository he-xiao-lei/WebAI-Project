package cloud.hexiaolei.webaiproject.exception;

import cloud.hexiaolei.webaiproject.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("服务端发生错误", e);
        return Result.error("出错啦" + e.toString());
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException duplicateKeyException) {
        log.error("异常格式", duplicateKeyException);
        String message = duplicateKeyException.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] split = errMsg.split(" ");
        Arrays.stream(split).forEach(System.out::println);
        String s = split[2];
        return Result.error(s + "已存在");
    }
}
