package com.balance.api.controller;

import com.balance.util.json.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by Bodil on 2016/9/23.
 * Api Controller Advice
 */
@ControllerAdvice(basePackages = "com.balance.api.controller")
@Order(0)
public class ApiControllerAdvice {
    private static final Logger api_logger = LoggerFactory.getLogger(ApiControllerAdvice.class);

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<JsonResult> handler(HttpClientErrorException exception) {
        return new ResponseEntity<>(new JsonResult(false, exception.getMessage()), exception.getStatusCode());
    }

    /**
     * 拦截@Valid请求参数验证不通过的异常
     *
     * @param exception 验证不通过的异常
     * @return 执行结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public JsonResult handler(MethodArgumentNotValidException exception) {
        api_logger.info("请求的参数不正确", exception);
        String validation_message;
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult != null && bindingResult.getFieldError() != null) {
            validation_message = bindingResult.getFieldError().getDefaultMessage();
        } else {
            validation_message = exception.getMessage();
        }
        return new JsonResult(false, validation_message);
    }

    /**
     * 拦截@Validated请求参数验证不通过的异常
     *
     * @param exception 验证不通过的异常
     * @return 执行结果
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public JsonResult handler(ConstraintViolationException exception) {
        api_logger.info("请求的参数不正确", exception);
        String validation_message;
        Set<ConstraintViolation<?>> constraintViolationSet = exception.getConstraintViolations();
        if (constraintViolationSet != null && constraintViolationSet.size() > 0) {
            ConstraintViolation violation = exception.getConstraintViolations().stream().findFirst().get();
            validation_message = violation.getMessage();
        } else {
            validation_message = exception.getMessage();
        }

        return new JsonResult(false, validation_message);
    }

    /**
     * 拦截@Valid请求参数验证不通过的异常
     *
     * @param exception 验证不通过的异常
     * @return 执行结果
     */
    @ExceptionHandler({BindException.class, HttpMessageNotReadableException.class,
            ServletRequestBindingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public JsonResult badRequestHandler(Exception exception) {
        api_logger.info("参数不正确", exception);
        return new JsonResult(false, exception.getMessage());
    }

}
