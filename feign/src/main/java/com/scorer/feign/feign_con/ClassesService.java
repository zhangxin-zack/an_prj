package com.scorer.feign.feign_con;

import com.scorer.feign.entity.ClassContent;
import com.scorer.feign.entity.Classes;
import com.scorer.feign.entity.Timetable;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "scorer-client")
public interface ClassesService {

    @RequestMapping(value = "/EDU/classes/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getClassesList(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/classes/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getClassesById(@RequestParam(value = "id") Integer id);

    @RequestMapping(value = "/EDU/classes/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addClasses(@RequestBody Classes classes);

    @RequestMapping(value = "/EDU/classes/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateClasses(@RequestBody Classes classes);

    @RequestMapping(value = "/EDU/classes/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteClasses(@RequestParam(value = "classesIds") List<Integer> classesIds);

    @RequestMapping(value = "/EDU/classes/select", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map selectClassesList(@RequestBody Classes classes);

    @RequestMapping(value = "/EDU/classes/list_timetable", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getTimetable(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/classes/add_timetable", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addTimetable(@RequestBody Timetable timetable);

    @RequestMapping(value = "/EDU/classes/list_class_content", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getClassContent(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/classes/add_class_content", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addClassContent(@RequestBody ClassContent classContent);

    @RequestMapping(value = "/EDU/classes/list_account_class", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getAccountClassesList(@RequestBody PageBean condition);
}
