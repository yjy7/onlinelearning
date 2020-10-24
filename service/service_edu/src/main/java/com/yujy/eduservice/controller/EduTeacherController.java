package com.yujy.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yujy.servicebase.exceptionHandler.GlobaExceptionHandler;
import com.yujy.servicebase.exceptionHandler.MyException;
import org.springframework.util.StringUtils;
import com.yujy.eduservice.entity.EduTeacher;
import com.yujy.eduservice.entity.vo.TeacherQuery;
import com.yujy.eduservice.service.EduTeacherService;
import com.yujy.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yujy
 * @since 2020-10-23
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduService/eduTeacher")
public class EduTeacherController {

    @Autowired
    EduTeacherService teacherService;

    /**
     * 查询所有老师
     *
     * @return
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> teachers = teacherService.list(null);
        try {
            int i = 10 / 0;
        }catch (Exception e){
            throw new MyException(20001,"执行了自定义异常处理");
        }

        return R.ok().data("items", teachers);
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")   //通过路径传值  用@PathVariable
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID") @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 分页查询讲师
     *
     * @param current 当前页
     * @param limit   每页记录数
     * @return
     */
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页") @PathVariable long current,
                             @ApiParam(name = "limit", value = "每页记录数") @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        IPage<EduTeacher> teacherIPage = teacherService.page(pageTeacher, null);
        //总记录数
        long total = teacherIPage.getTotal();
        //记录
        List<EduTeacher> records = teacherIPage.getRecords();
        //当前页
        long pageCurrent = teacherIPage.getCurrent();
        HashMap<String, Object> map = new HashMap<>();
        map.put("总页数", total);
        map.put("记录", records);
        map.put("当前页", pageCurrent);
        return R.ok().data(map);
    }

    @ApiOperation(value = "条件查询讲师")
    @PostMapping("pageTeacherConditjion/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current", value = "当前页") @PathVariable long current,
                                  @ApiParam(name = "limit", value = "每页记录数") @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建分页对象
        Page<EduTeacher> page = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //判断条件是否为空，不为空拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        IPage<EduTeacher> teacherIPage = teacherService.page(page, wrapper);
        //总记录数
        long total = teacherIPage.getTotal();
        //记录
        List<EduTeacher> records = teacherIPage.getRecords();
        //当前页
        long pageCurrent = teacherIPage.getCurrent();
        HashMap<String, Object> map = new HashMap<>();
        map.put("总页数", total);
        map.put("记录", records);
        map.put("当前页", pageCurrent);
        return R.ok().data(map);
    }

    /**
     * 添加讲师
     *
     * @return
     */
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据id查询讲师
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@ApiParam(name = "id", value = "讲师ID") @PathVariable String id) {

        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }

    /**
     *
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value = "更新讲师信息")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }

    }

}

