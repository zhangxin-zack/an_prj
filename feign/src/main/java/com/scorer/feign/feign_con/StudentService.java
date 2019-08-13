package com.scorer.feign.feign_con;

import com.scorer.feign.entity.Student;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "scorer-client")
public interface StudentService {

    @RequestMapping(value = "/EDU/student/bk/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getStudentListBK(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/student/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getStudentList(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/student/parent_get", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getStudentParentDetail(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/student/alarm_get", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getRingAlarmDetail(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/student/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addStudent(@RequestBody Student student);

    @RequestMapping(value = "/EDU/student/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateStudent(@RequestBody Student student);

    @RequestMapping(value = "/EDU/student/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteStudent(@RequestParam(value = "studentIds") List<Integer> studentIds);


}
