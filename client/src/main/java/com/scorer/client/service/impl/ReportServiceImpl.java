package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ClassesDao;
import com.scorer.client.dao.mysql_dao1.ReportDao;
import com.scorer.client.entity.Classes;
import com.scorer.client.service.ClassesService;
import com.scorer.client.service.ReportService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl extends BaseSeviceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public Map<String, Object> getAlarmReportList(PageBean page) {
        try{
            page.setTotal(reportDao.getAlarmReportCount(page));
            page.setRows(reportDao.getAlarmReportList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getActualReportList(PageBean page) {
        try{
            page.setTotal(reportDao.getDailyReportCount(page));
            page.setRows(reportDao.getDailyReportList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getHistoryReportList(PageBean page) {
        try{
            page.setTotal(reportDao.getHistoryReportCount(page));
            page.setRows(reportDao.getHistoryReportList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }


}
