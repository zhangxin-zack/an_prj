package com.scorer.client.controller;

import com.scorer.client.entity.Manager;
import com.scorer.client.entity.School;
import com.scorer.client.entity.SchoolMenu;
import com.scorer.client.service.ManagerService;
import com.scorer.client.service.SchoolService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/school")
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
    public Map deleteSchool(@RequestParam(value = "schoolIds") List<Long> schoolIds) {
        return schoolService.deleteSchool(schoolIds);
    }

    @RequestMapping(value = "/get")
    public Map getSchoolById(@RequestBody School school) {
        return schoolService.getSchoolByCondition(school);
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

    @RequestMapping(value = "/sc_menu_add")
    public Map addSchoolMenu(@RequestBody SchoolMenu menu) {
        return schoolService.addSchoolMenu(menu);
    }

    @RequestMapping(value = "/sc_menu_update")
    public Map updateSchoolMenu(@RequestBody SchoolMenu menu) {
        return schoolService.updateSchoolMenu(menu);
    }

    @RequestMapping(value = "/sc_menu_delete")
    public Map deleteSchoolMenu(@RequestParam(value = "menuIds") List<Long> schoolMenuIds) {
        return schoolService.deleteSchoolMenu(schoolMenuIds);
    }

    @RequestMapping(value = "/sc_menu_list")
    public Map getSchoolMenuList(@RequestBody PageBean page) {
        return schoolService.getSchoolMenuList(page);
    }

    @RequestMapping(value = "/sc_menu_tree")
    public Map getSchoolMenuTree() {
        return schoolService.getSchoolMenuTree();
    }


}