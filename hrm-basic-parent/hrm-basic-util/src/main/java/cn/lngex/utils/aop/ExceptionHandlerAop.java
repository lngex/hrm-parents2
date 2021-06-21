package cn.lngex.utils.aop;

import cn.lngex.utils.AjaxResult;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Component
public class ExceptionHandlerAop {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult MethodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<Object, Object> map = new HashMap<>();
        fieldErrors.stream().forEach(fieldError->{
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return AjaxResult.me().setMessage(map.toString()).setSuccess(false);
    }

    @ExceptionHandler(Exception.class)
    public AjaxResult excetionHandle(Exception e){
        e.printStackTrace();
        System.out.println("================================");
        return AjaxResult.me().setSuccess(false).setMessage("系统繁忙");
    }
}
