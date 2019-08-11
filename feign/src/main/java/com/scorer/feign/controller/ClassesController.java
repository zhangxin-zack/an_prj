package com.scorer.feign.controller;

import com.scorer.feign.entity.ClassContent;
import com.scorer.feign.entity.Classes;
import com.scorer.feign.entity.Timetable;
import com.scorer.feign.feign_con.ClassesService;
import com.scorer.feign.values.PageBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/classes")
public class ClassesController {

    @Resource
    private ClassesService classesService;

    @RequestMapping(value = "/list")
    public Map getClassesList(@RequestBody PageBean condition) {
        return classesService.getClassesList(condition);
    }

    @RequestMapping(value = "/get")
    public Map getClassesById(@RequestParam(value = "id") Integer id) {
        return classesService.getClassesById(id);
    }

    @RequestMapping(value = "/add")
    public Map addClasses(@RequestBody Classes classes) {
        return classesService.addClasses(classes);
    }

    @RequestMapping(value = "/update")
    public Map updateClasses(@RequestBody Classes classes) {
        return classesService.updateClasses(classes);
    }

    @RequestMapping(value = "/remove")
    public Map deleteClasses(@RequestParam(value = "classesIds") List<Integer> classesIds) {
        return classesService.deleteClasses(classesIds);
    }

    @RequestMapping(value = "/list_timetable")
    public Map getTimetable(@RequestBody PageBean condition) {
        return classesService.getTimetable(condition);
    }

    @RequestMapping(value = "/add_timetable")
    public Map addTimetable(@RequestBody Timetable timetable) {
        return classesService.addTimetable(timetable);
    }

    @RequestMapping(value = "/list_class_content")
    public Map getClassContent(@RequestBody PageBean condition) {
        return classesService.getClassContent(condition);
    }

    @RequestMapping(value = "/add_class_content")
    public Map getClassContent(@RequestBody ClassContent classContent) {
        return classesService.addClassContent(classContent);
    }
}