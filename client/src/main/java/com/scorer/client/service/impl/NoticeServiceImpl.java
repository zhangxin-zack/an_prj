package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.NoticeDao;
import com.scorer.client.entity.Notice;
import com.scorer.client.service.NoticeService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl extends BaseSeviceImpl implements NoticeService {

    @Autowired
    private NoticeDao NoticeDao;

    @Override
    public Map<String, Object> getNoticeList(PageBean page) {
        try{
            page.setTotal(NoticeDao.getNoticeCount(page));
            page.setRows(NoticeDao.getNoticeList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getNoticeById(Integer NoticeId) {
        try{
            Notice Notice = NoticeDao.getNoticeById(NoticeId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", Notice);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addNotice(Notice notice) {
        try{
            NoticeDao.addNotice(notice);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateNotice(Notice notice) {
        try{
            NoticeDao.updateNotice(notice);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteNotice(List noticeIds) {
        try{
            NoticeDao.deleteNotice(noticeIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }
}
