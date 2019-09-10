package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.AlarmReport;
import com.scorer.client.entity.Classes;
import com.scorer.client.entity.DailyReport;
import com.scorer.client.values.PageBean;
import org.apache.ibatis.annotations.Param;
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

    int getArriveCount(PageBean page);

    int getStudentCount(PageBean page);

    int getRingCount(PageBean page);

    int getStudentCountClass(Classes classes);

    int getRingCountClass(Classes classes);

    int getArriveCountClass(@Param("page") PageBean page, @Param("classes") Classes classes);
}
