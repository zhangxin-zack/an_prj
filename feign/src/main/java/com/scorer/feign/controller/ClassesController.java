package com.scorer.feign.controller;

import com.scorer.feign.entity.ClassContent;
import com.scorer.feign.entity.ClassTime;
import com.scorer.feign.entity.Classes;
import com.scorer.feign.entity.Timetable;
import com.scorer.feign.feign_con.ClassesService;
import com.scorer.feign.values.PageBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
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

    @RequestMapping(value ="/upload_timetable")
    public Map uploadTimetable(@RequestParam(value = "classId") Long classId,
                               @RequestPart(value = "timetableFile") MultipartFile timetableFile,
                               @RequestParam(value = "startDate") Long startDate) {
        return classesService.uploadTimetable(classId,timetableFile,startDate);
    }

    @RequestMapping(value ="/modify_timetable")
    public Map modifyTimetable(@RequestParam(value = "classId") Long classId,
                               @RequestParam(value = "timetable") String timetable,
                               @RequestParam(value = "startDate") Long startDate) {
        return classesService.modifyTimetable(classId,timetable,startDate);
    }

    @RequestMapping(value = "/download_timetable_temp")
    public ResponseEntity<byte[]> downloadTimetableTemp(){
        return classesService.downloadTimetableTemp();
    }

    @RequestMapping(value = "/list_timetable_class")
    public Map getTimetableClass(@RequestBody PageBean condition) {
        return classesService.getTimetableClass(condition);
    }


    @RequestMapping(value = "/list_class_time")
    public Map getClassTimeList(@RequestBody PageBean condition) {
        return classesService.getClassTimeList(condition);
    }

    @RequestMapping(value = "/add_class_time")
    public Map addClassTime(@RequestBody ClassTime classTime) {
        return classesService.addClassTime(classTime);
    }

    @RequestMapping(value = "/update_class_time")
    public Map updateClassTime(@RequestBody ClassTime classTime) {
        return classesService.updateClassTime(classTime);
    }

    @RequestMapping(value = "/remove_class_time")
    public Map deleteClassTime(@RequestParam(value = "classTimeIds") List<Integer> classTimeIds) {
        return classesService.deleteClassTime(classTimeIds);
    }
}