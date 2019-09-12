package com.scorer.client.controller;

import com.scorer.client.entity.Student;
import com.scorer.client.service.StudentService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/bk/list")
    public Map getStudentListBK(@RequestBody PageBean condition) {
        return studentService.getStudentListBK(condition);
    }


    @RequestMapping(value = "/list")
    public Map getStudentList(@RequestBody PageBean condition) {
        return studentService.getStudentList(condition);
    }

    @RequestMapping(value = "/list_app")
    public Map getStudentListForApp(@RequestBody PageBean condition) {
        return studentService.getStudentListForApp(condition);
    }

    @RequestMapping(value = "/get_family")
    public Map getStudentFamily(@RequestBody PageBean condition) {
        return studentService.getStudentFamily(condition);
    }

    @RequestMapping(value = "/alarm_get")
    public Map getRingAlarmDetail(@RequestBody PageBean condition) {
        return studentService.getRingAlarmDetail(condition);
    }

    @RequestMapping(value = "/add")
    public Map addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @RequestMapping(value = "/update")
    public Map updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @RequestMapping(value = "/remove")
    public Map deleteStudent(@RequestParam(value = "studentIds") List<Integer> studentIds) {
        return studentService.deleteStudent(studentIds);
    }

}