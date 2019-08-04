package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.AlarmReport;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmReportDao {

    long getAlarmReportCount(PageBean page) throws Exception;

    List<AlarmReport> getAlarmReportList(PageBean page) throws Exception;

    AlarmReport getAlarmReportById(Integer alarmReportId) throws Exception;

    void addAlarmReport(AlarmReport alarmReport) throws Exception;

    void updateAlarmReport(AlarmReport alarmReport) throws Exception;

    void deleteAlarmReport(List alarmReportIds) throws Exception;

}
