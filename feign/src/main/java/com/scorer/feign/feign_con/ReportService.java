package com.scorer.feign.feign_con;

import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
@FeignClient(value = "scorer-client")
public interface ReportService {
    @RequestMapping(value = "/EDU/report/alarm_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getAlarmReportList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/report/actual_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getActualReportList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/report/history_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getHistoryReportList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/report/daily_report_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getDailyReportList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/report/daily_report_list_school", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getDailyReportSchoolList(@RequestBody PageBean page);
}
