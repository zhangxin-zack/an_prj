package com.scorer.client.controller;

import com.scorer.client.entity.ClassContent;
import com.scorer.client.entity.Classes;
import com.scorer.client.entity.Timetable;
import com.scorer.client.service.ClassesService;
import com.scorer.client.tools.ExcelUtils;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/classes")
public class ClassesController {

    @Autowired
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

    @RequestMapping(value = "/select")
    public Map selectClassesList(@RequestBody Classes classes) {
        return classesService.selectClassesList(classes);
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

    @RequestMapping(value = "/list_account_class")
    public Map getClassListContent(@RequestBody PageBean condition) {
        return classesService.getAccountClassesList(condition);
    }

    @RequestMapping(value = "/upload_timetable")
    public Map uploadTimetable(Timetable timetable) {
        MultipartFile timetableFile = timetable.getTimetableFile();
        if(!timetableFile.isEmpty()){
            timetable.setTimetable(ExcelUtils.getTimetableData(timetableFile));
        }
        return classesService.addTimetable(timetable);
    }

    @RequestMapping(value = "/download_timetable_temp")
    public ResponseEntity<byte[]> downloadTimetableTemp(HttpServletResponse response) {
        return ExcelUtils.exportFile(response);
    }

    @RequestMapping(value = "/list_timetable_class")
    public Map getTimetableClass(@RequestBody PageBean condition) {
        return classesService.getTimetableClass(condition);
    }


}