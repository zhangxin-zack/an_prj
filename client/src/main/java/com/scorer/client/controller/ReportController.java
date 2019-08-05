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

    @RequestMapping(value = "/alarm_list")
    public Map getAlarmReportList(@RequestBody PageBean page) {
        return reportService.getAlarmReportList(page);
    }

    @RequestMapping(value = "/actual_list")
    public Map getActualReportList(@RequestBody PageBean page) {
        return reportService.getActualReportList(page);
    }

    @RequestMapping(value = "/history_list")
    public Map getHistoryReportList(@RequestBody PageBean page) {
        return reportService.getHistoryReportList(page);
    }

}