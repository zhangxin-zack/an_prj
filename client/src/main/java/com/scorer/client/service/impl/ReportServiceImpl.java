package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ClassesDao;
import com.scorer.client.dao.mysql_dao1.ReportDao;
import com.scorer.client.entity.ArriveCount;
import com.scorer.client.entity.Classes;
import com.scorer.client.service.ReportService;
import com.scorer.client.service.impl.BaseSeviceImpl;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ReportServiceImpl extends BaseSeviceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;
    @Resource
    private ClassesDao classesDao;

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

    @Override
    public Map<String, Object> getDailyReportList(PageBean page) {
        try{
            ArriveCount arriveCount = new ArriveCount();
            arriveCount.setStudentCount(reportDao.getStudentCount(page));
            arriveCount.setRingCount(reportDao.getRingCount(page));
            arriveCount.setArriveCount(reportDao.getArriveCount(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", arriveCount);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getDailyReportSchoolList(PageBean page) {
        try{
            page.setTotal(classesDao.getClassesCount(page));
            List<Classes> classesList = classesDao.getClassesList(page);
            List rows = new ArrayList();
            for(Classes classes : classesList){
                ArriveCount arriveCount = new ArriveCount();
                arriveCount.setStudentCount(reportDao.getStudentCountClass(classes));
                arriveCount.setRingCount(reportDao.getRingCountClass(classes));
                if(Objects.equals(page.getSearchs().get("historyType"),1)||Objects.equals(page.getSearchs().get("historyType"),"1")){
                    arriveCount.setArriveCount(reportDao.getArriveCountClass(page,classes));
                }else{
                    arriveCount.setArriveAvg(reportDao.getArriveCountMonthClass(page,classes));
                }
                rows.add(arriveCount);
            }
            page.setRows(rows);
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }


}
