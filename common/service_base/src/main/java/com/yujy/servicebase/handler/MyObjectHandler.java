package com.yujy.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yujy
 * @title:
 * @projectName
 * @description:
 * @date 2020/10/2322:11
 */
@Component
public class MyObjectHandler implements MetaObjectHandler {
    /**
     * 使用mp实现添加操作，执行此方法
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);

        this.setFieldValByName("isDeleted",0,metaObject);

    }

    /**
     * 使用mp实现修改操作，执行此方法
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}