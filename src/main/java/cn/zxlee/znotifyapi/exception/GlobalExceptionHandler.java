package cn.zxlee.znotifyapi.exception;

import cn.zxlee.znotifyapi.enums.ResponseStatus;
import cn.zxlee.znotifyapi.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-27 18:21
 **/

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handleException(HttpServletRequest request, Exception exception) {
        String exceptionStr = exception.getMessage();
        Result result = null;
        if (exception instanceof MethodArgumentNotValidException || exception instanceof ConstraintViolationException
            || exception instanceof BindException) {
            if (exception instanceof MethodArgumentNotValidException) {
                // BeanValidation exception
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
                exceptionStr = ex.getBindingResult().getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.joining("; "));
            } else if (exception instanceof ConstraintViolationException) {
                // BeanValidation GET simple param
                ConstraintViolationException ex = (ConstraintViolationException) exception;
                exceptionStr = ex.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining("; "));
            } else {
                // BeanValidation GET object param
                BindException ex = (BindException) exception;
                exceptionStr = ex.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.joining("; "));
            }
            result = Result.fail(ResponseStatus.USER_PARAM_ERROR.getCode(),exceptionStr);
        } else if (exception instanceof LoginAuthErrorException) {
            result = Result.fail(ResponseStatus.USER_LOGIN_AUTH_ERROR.getCode(),ResponseStatus.USER_LOGIN_AUTH_ERROR.getMsg());
        } else {
            exception.printStackTrace();
            log.error("exception ====>" + exceptionStr);
            result = Result.fail(exceptionStr);
        }
//        exception.printStackTrace();

        return result;
    }

}
