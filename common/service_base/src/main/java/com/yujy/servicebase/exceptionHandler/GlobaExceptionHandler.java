package com.yujy.servicebase.exceptionHandler;



import com.yujy.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yujy
 * @title:
 * @projectName
 * @description: 统一异常处理
 * @date 2020/10/2414:50
 */
@ControllerAdvice
@Slf4j
public class GlobaExceptionHandler {

    @ExceptionHandler(Exception.class)//指定出现什么异常执行这个方法
    @ResponseBody//返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局处理异常");
    }


    /**
     * 特定异常
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)//指定出现什么异常执行这个方法
    @ResponseBody//返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException处理异常");
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)//指定出现什么异常执行这个方法
    @ResponseBody//返回数据
    public R error(MyException e){
        e.printStackTrace();
        log.error(e.getMsg());
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
