package com.scorer.feign.controller;

import com.scorer.feign.entity.Teacher;
import com.scorer.feign.feign_con.TeacherService;
import com.scorer.feign.values.PageBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/Scorer/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @RequestMapping(value = "/list")
    public Map getTeacherList(@RequestBody PageBean condition) {
        return teacherService.getTeacherList(condition);
    }

    @RequestMapping(value = "/get")
    public Map getTeacherById(@RequestBody Long id) {
        return teacherService.getTeacherById(id);
    }

    @RequestMapping(value = "/add")
    public Map addTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @RequestMapping(value = "/update")
    public Map updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @RequestMapping(value = "/remove")
    public Map deleteTeacher(@RequestBody List<Long> teacherIds) {
        return teacherService.deleteTeacher(teacherIds);
    }

}