package org.bigdou.dbm.config;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述: 全局异常捕获
 *
 * @author xuqing F00722
 * @date 2021/9/2 15:08
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus
    public Response handleException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException", e);
        return Response.buildFailure("HttpRequestMethodNotSupportedException","不支持请求方法[" + e.getMethod() + "]");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus
    public Response handleException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException", e);
        return Response.buildFailure("HttpMessageNotReadableException","请求体不能为空");
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus
    public Response illegalArgumentHandler(IllegalArgumentException e) {
        log.error("IllegalArgumentException", e);
        return Response.buildFailure("IllegalArgumentException", e.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus
    public Response constraintViolationHandler(ConstraintViolationException e) {
        log.error("ConstraintViolationException", e);
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList.toArray(), ";");
        return Response.buildFailure("ConstraintViolationException", messages);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus
    public Response methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        String err = e.getBindingResult().getFieldError() != null ? e.getBindingResult().getFieldError().getDefaultMessage() : e.getMessage();
        log.error("MethodArgumentNotValidException", e);
        return Response.buildFailure("MethodArgumentNotValidException", err);
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseStatus
    public Response baseExceptionHandler(BaseException e) {
        log.error("BaseException", e);
        return Response.buildFailure(e.getErrCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus
    public Response exceptionHandler(Exception e) {
        log.error("Exception", e);
        return Response.buildFailure("server error", "服务端异常");
    }

}
