package com.scorer.feign.feign_con;

import com.scorer.feign.entity.School;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "scorer-client")
public interface SchoolService {

    @RequestMapping(value = "/EDU/school/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getSchoolList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/school/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addSchool(@RequestBody School school);

    @RequestMapping(value = "/EDU/school/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateSchool(@RequestBody School school);

    @RequestMapping(value = "/EDU/school/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteSchool(@RequestParam(value = "schoolIds") List<Long> schoolIds);

    @RequestMapping(value = "/EDU/school/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getSchoolById(@RequestBody School school);

    @RequestMapping(value = "/EDU/school/select", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getSchoolByCondition(@RequestBody School school);

    @RequestMapping(value = "/EDU/school/get_detail", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getSchoolDetail(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/school/get_report", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getSchoolReport(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/school/stat_class", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getClassCountBySchoolId(@RequestParam(value = "schoolId") Long schoolId);

    @RequestMapping(value = "/EDU/school/stat_students", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getClassStudentCountBySchoolId(@RequestParam(value = "schoolId") Long schoolId);

}
