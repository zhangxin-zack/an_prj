package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.AlarmReport;
import com.scorer.client.entity.DailyReport;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDao {

    Long getAlarmReportCount(PageBean page) throws Exception;

    List<AlarmReport> getAlarmReportList(PageBean page) throws Exception;

    Long getDailyReportCount(PageBean page) throws Exception;

    List<DailyReport> getDailyReportList(PageBean page) throws Exception;

    Long getHistoryReportCount(PageBean page) throws Exception;

    List<DailyReport> getHistoryReportList(PageBean page) throws Exception;
}
