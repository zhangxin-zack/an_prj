package com.scorer.client.controller;

import com.scorer.client.entity.Teacher;
import com.scorer.client.service.TeacherService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/list")
    public Map getTeacherList(@RequestBody PageBean condition) {
        return teacherService.getTeacherList(condition);
    }

    @RequestMapping(value = "/get")
    public Map getTeacherById(@RequestParam(value = "id") Integer id) {
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
    public Map deleteTeacher(@RequestParam("teacherIds") List<Integer> teacherIds,
                             @RequestParam("schoolId") Long schoolId) {
        return teacherService.deleteTeacher(teacherIds,schoolId);
    }

    //有问题
    /*@RequestMapping(value = "/get_head")
    public Map getHeadTeacherList(@RequestParam("teacherIds") Map<String, Object> params) {
        return teacherService.getHeadTeacherList(params);
    }*/


}