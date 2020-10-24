package com.yujy.servicebase.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yujy
 * @title:
 * @projectName
 * @description:
 * @date 2020/10/2420:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

}
