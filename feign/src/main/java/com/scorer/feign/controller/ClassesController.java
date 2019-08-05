package com.scorer.feign.controller;

import com.scorer.feign.entity.Classes;
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
}