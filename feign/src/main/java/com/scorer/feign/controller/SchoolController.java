package com.scorer.feign.controller;

import com.scorer.feign.entity.School;
import com.scorer.feign.feign_con.SchoolService;
import com.scorer.feign.values.PageBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/school")
public class SchoolController {

    @Resource
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
    public Map deleteSchool(@RequestParam(value = "schoolIds") List<Long> schoolIds) {
        return schoolService.deleteSchool(schoolIds);
    }

    @RequestMapping(value = "/get")
    public Map getSchoolById(@RequestBody School school) {
        return schoolService.getSchoolById(school);
    }

    @RequestMapping(value = "/select")
    public Map getSchoolByCondition(@RequestBody School school) {
        return schoolService.getSchoolByCondition(school);
    }

    @RequestMapping(value = "/get_detail")
    public Map getSchoolDetail(@RequestBody PageBean page) {
        return schoolService.getSchoolDetail(page);
    }

    @RequestMapping(value = "/get_report")
    public Map getSchoolReport(@RequestBody PageBean page) {
        return schoolService.getSchoolReport(page);
    }

    @RequestMapping(value = "/stat_class")
    public Map getClassCountById(@RequestParam(value = "schoolId") Long schoolId) {
        return schoolService.getClassCountBySchoolId(schoolId);
    }

    @RequestMapping(value = "/stat_students")
    public Map getStudentCountById(@RequestParam(value = "schoolId") Long schoolId) {
        return schoolService.getClassStudentCountBySchoolId(schoolId);
    }

}