package com.yujy.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yujy
 * @title:
 * @projectName
 * @description: 讲师条件查询
 * @date 2020/10/2321:33
 */
@Data
public class TeacherQuery {
    @ApiModelProperty(value = "讲师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师，2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间",example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间",example = "2019-01-01 10:10:10")
    private String end;

}
