package com.scorer.feign.feign_con;

import com.scorer.feign.entity.Teacher;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "scorer-client")
public interface TeacherService {

    @RequestMapping(value = "/Scorer/teacher/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getTeacherList(@RequestBody PageBean condition);

    @RequestMapping(value = "/Scorer/teacher/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getTeacherById(@RequestBody Long id);

    @RequestMapping(value = "/Scorer/teacher/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addTeacher(@RequestBody Teacher teacher);

    @RequestMapping(value = "/Scorer/teacher/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateTeacher(@RequestBody Teacher teacher);

    @RequestMapping(value = "/Scorer/teacher/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteTeacher(@RequestBody List<Long> teacherIds);

}
