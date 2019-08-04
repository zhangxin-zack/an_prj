package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.AlarmReport;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmReportDao {

    public long getAlarmReportCount(PageBean page) throws Exception;

    public List<AlarmReport> getAlarmReportList(PageBean page) throws Exception;

    public AlarmReport getAlarmReportById(Integer alarmReportId) throws Exception;

    public void addAlarmReport(AlarmReport alarmReport) throws Exception;

    public void updateAlarmReport(AlarmReport alarmReport) throws Exception;

    public void deleteAlarmReport(List alarmReportIds) throws Exception;

}
