package com.scorer.client.service;

import com.scorer.client.values.PageBean;

import java.util.Map;


public interface ReportService {

    Map<String, Object> getAlarmReportList(PageBean page);

    Map<String, Object> getActualReportList(PageBean page);

    Map<String, Object> getHistoryReportList(PageBean page);

    Map<String, Object> getDailyReportList(PageBean page);

    Map<String, Object> getDailyReportSchoolList(PageBean page);
}
