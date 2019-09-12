package com.scorer.feign.controller;

import com.scorer.feign.feign_con.ReportService;
import com.scorer.feign.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/EDU/report")
public class ReportController {

    @Resource
    private ReportService reportService;

    @RequestMapping(value = "/alarm_list")
    public Map getAlarmReportList(@RequestBody PageBean page) {
        return reportService.getAlarmReportList(page);
    }

    @RequestMapping(value = "/alarm_count")
    public Map getAlarmReportCount(@RequestBody PageBean page) {
        return reportService.getAlarmReportCount(page);
    }

    @RequestMapping(value = "/actual_list")
    public Map getActualReportList(@RequestBody PageBean page) {
        return reportService.getActualReportList(page);
    }

    @RequestMapping(value = "/history_list")
    public Map getHistoryReportList(@RequestBody PageBean page) {
        return reportService.getHistoryReportList(page);
    }

    @RequestMapping(value = "/daily_report_list")
    public Map getDailyReportList(@RequestBody PageBean page) {
        return reportService.getDailyReportList(page);
    }

    @RequestMapping(value = "/daily_report_list_school")
    public Map getDailyReportSchoolList(@RequestBody PageBean page) {
        return reportService.getDailyReportSchoolList(page);
    }

    @RequestMapping(value = "/school_student_list")
    public Map getSchoolStudentList(@RequestBody PageBean page) {
        return reportService.getSchoolStudentList(page);
    }
}