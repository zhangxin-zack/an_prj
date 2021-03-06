package com.scorer.client.controller;

import com.scorer.client.service.ReportService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/EDU/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 获取警报信息
     * @param page
     * @return
     */
    @RequestMapping(value = "/alarm_list")
    public Map getAlarmReportList(@RequestBody PageBean page) {
        return reportService.getAlarmReportList(page);
    }


    /**
     * 获取警报信息(数量汇总)
     * @param page
     * @return
     */
    @RequestMapping(value = "/alarm_count")
    public Map getAlarmReportCount(@RequestBody PageBean page) {
        return reportService.getAlarmReportCount(page);
    }

    /**
     * 获取日报表
     * @param page
     * @return
     */
    @RequestMapping(value = "/actual_list")
    public Map getActualReportList(@RequestBody PageBean page) {
        return reportService.getActualReportList(page);
    }

    /**
     * 获取历史记录(当日/当月)
     * @param page
     * @return
     */
    @RequestMapping(value = "/history_list")
    public Map getHistoryReportList(@RequestBody PageBean page) {
        return reportService.getHistoryReportList(page);
    }


    /**
     * 当日到校情况
     * @param page
     * @return
     */
    @RequestMapping(value = "/daily_report_list")
    public Map getDailyReportList(@RequestBody PageBean page) {
        return reportService.getDailyReportList(page);
    }

    /**
     * 当日到校情况报表
     * @param page
     * @return
     */
    @RequestMapping(value = "/daily_report_list_school")
    public Map getDailyReportSchoolList(@RequestBody PageBean page) {
        return reportService.getDailyReportSchoolList(page);
    }

    /**
     * 当前学生情况
     * @param page
     * @return
     */
    @RequestMapping(value = "/school_student_list")
    public Map getSchoolStudentList(@RequestBody PageBean page) {
        return reportService.getSchoolStudentList(page);
    }

}