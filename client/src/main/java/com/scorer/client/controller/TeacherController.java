package com.scorer.client.controller;

import com.scorer.client.entity.Teacher;
import com.scorer.client.service.TeacherService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/list")
    public Map getTeacherList(PageBean condition) {
        return teacherService.getTeacherList(condition);
    }

    @RequestMapping(value = "/get")
    public Map getTeacherById(Integer id) {
        return teacherService.getTeacherById(id);
    }

    @RequestMapping(value = "/add")
    public Map addTeacher(Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @RequestMapping(value = "/update")
    public Map updateTeacher(Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @RequestMapping(value = "/remove")
    public Map deleteTeacher(List<Integer> teacherIds) {
        return teacherService.deleteTeacher(teacherIds);
    }
}