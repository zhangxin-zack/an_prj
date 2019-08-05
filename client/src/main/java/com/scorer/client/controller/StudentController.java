package com.scorer.client.controller;

import com.scorer.client.entity.Student;
import com.scorer.client.service.StudentService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Scorer/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/list")
    public Map getStudentList(PageBean condition) {
        return studentService.getStudentList(condition);
    }
    @RequestMapping(value = "/parent_get")
    public Map getStudentParentDetail(PageBean condition) {
        return studentService.getStudentParentDetail(condition);
    }
    @RequestMapping(value = "/alarm_get")
    public Map getRingAlarmDetail(PageBean condition) {
        return studentService.getRingAlarmDetail(condition);
    }

    @RequestMapping(value = "/add")
    public Map addStudent(Student student) {
        return studentService.addStudent(student);
    }

    @RequestMapping(value = "/update")
    public Map updateStudent(Student student) {
        return studentService.updateStudent(student);
    }

    @RequestMapping(value = "/remove")
    public Map deleteStudent(List<Integer> StudentIds) {
        return studentService.deleteStudent(StudentIds);
    }
}