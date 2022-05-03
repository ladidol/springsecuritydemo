package com.feng.handler;

import com.feng.utils.result.AppException;
import com.feng.utils.result.R;
import com.feng.utils.result.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


//@ControllerAdvice//这里就不适用, 全局异常捕获了
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public Result error(AppException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.fail(e.getMessage());
    }
}
