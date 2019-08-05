package com.scorer.client.controller;

import com.scorer.client.entity.School;
import com.scorer.client.service.SchoolService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Scorer/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "/list")
    public Map getSchoolList(@RequestBody PageBean page) {
        return schoolService.getSchoolList(page);
    }

    @RequestMapping(value = "/add")
    public Map addSchool(@RequestBody School school) {
        return schoolService.addSchool(school);
    }

    @RequestMapping(value = "/update")
    public Map updateSchool(@RequestBody School school) {
        return schoolService.updateSchool(school);
    }

    @RequestMapping(value = "/remove")
    public Map deleteSchool(@RequestParam(value = "SchoolIds[]") List<Long> schoolIds) {
        return schoolService.deleteSchool(schoolIds);
    }

    @RequestMapping(value = "/get")
    public Map getSchoolById(@RequestBody Long schoolId) {
        return schoolService.getSchoolById(schoolId);
    }

    @RequestMapping(value = "/stat_class")
    public Map getClassCountById(@RequestBody Long schoolId) {
        return schoolService.getClassCountBySchoolId(schoolId);
    }

    @RequestMapping(value = "/stat_students")
    public Map getStudentCountById(@RequestBody Long schoolId) {
        return schoolService.getClassStudentCountBySchoolId(schoolId);
    }

}