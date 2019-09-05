package com.scorer.feign.feign_con;

import com.scorer.feign.entity.ClassContent;
import com.scorer.feign.entity.ClassTime;
import com.scorer.feign.entity.Classes;
import com.scorer.feign.entity.Timetable;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Collection;
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

    @RequestMapping(value = "/EDU/classes/upload_timetable",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map uploadTimetable(@RequestParam(value = "classId") Long classId,
                        @RequestPart(value = "timetableFile") MultipartFile timetableFile,
                        @RequestParam(value = "startDate") Long startDate);

    @RequestMapping(value = "/EDU/classes/upload_timetable", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map modifyTimetable(@RequestParam(value = "classId") Long classId,
                        @RequestParam(value = "timetable") String timetable,
                        @RequestParam(value = "startDate") Long startDate);

    @RequestMapping(value = "/EDU/classes/download_timetable_temp")
    ResponseEntity<byte[]> downloadTimetableTemp();

    @RequestMapping(value = "/EDU/classes/list_timetable_class", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getTimetableClass(PageBean condition);

    @RequestMapping(value = "/EDU/classes/list_class_group_parent", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Long> getListClassStudentParent(@RequestParam(value = "classId") long classId);

    @RequestMapping(value = "/EDU/classes/list_student_group_parent", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Long> getListStudentParent(@RequestParam(value = "studentId") long studentId);


    @RequestMapping(value = "/EDU/classes/list_class_time", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getClassTimeList(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/classes/add_class_time", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addClassTime(@RequestBody ClassTime classTime);

    @RequestMapping(value = "/EDU/classes/update_class_time", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateClassTime(@RequestBody ClassTime classTime);

    @RequestMapping(value = "/EDU/classes/remove_class_time", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteClassTime(@RequestParam(value = "classTimeIds") List<Integer> classTimeIds);


}
