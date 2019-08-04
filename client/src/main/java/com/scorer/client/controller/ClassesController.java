package com.scorer.client.controller;

import com.scorer.client.entity.Classes;
import com.scorer.client.service.ClassesService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Scorer/classes")
public class ClassesController {

    @Autowired
    private ClassesService ClassesService;

    @RequestMapping(value = "/list")
    public Map getClassesList(@RequestBody PageBean condition) {
        return ClassesService.getClassesList(condition);
    }

    @RequestMapping(value = "/get")
    public Map getClassesById(@RequestBody Integer id) {
        return ClassesService.getClassesById(id);
    }

    @RequestMapping(value = "/add")
    public Map addClasses(@RequestBody Classes classes) {
        return ClassesService.addClasses(classes);
    }

    @RequestMapping(value = "/update")
    public Map updateClasses(@RequestBody Classes classes) {
        return ClassesService.updateClasses(classes);
    }

    @RequestMapping(value = "/remove")
    public Map deleteClasses(@RequestBody List<Integer> classesIds) {
        return ClassesService.deleteClasses(classesIds);
    }
}