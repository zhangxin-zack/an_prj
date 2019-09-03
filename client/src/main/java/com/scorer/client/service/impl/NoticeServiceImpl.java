package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.NoticeDao;
import com.scorer.client.dao.mysql_dao1.StudentDao;
import com.scorer.client.entity.DailyRecommend;
import com.scorer.client.entity.Notice;
import com.scorer.client.entity.RecommendCategory;
import com.scorer.client.entity.Student;
import com.scorer.client.service.NoticeService;
import com.scorer.client.service.impl.BaseSeviceImpl;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl extends BaseSeviceImpl implements NoticeService {

    @Autowired
    private NoticeDao NoticeDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public Map<String, Object> getNoticeList(PageBean page) {
        try{
            if(page.getSearchs().get("studentId")!=null){
                Student student = studentDao.getStudentById(Long.valueOf(page.getSearchs().get("studentId").toString()));
                page.getSearchs().put("classId", student.getClassId());
            }
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
            if(notice.getClassIds()==null){
                notice.setClassIds(new ArrayList<>());
            }
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

    @Override
    public Map<String, Object> getDailyInfoList(PageBean page) {
        try{
            page.setTotal(NoticeDao.getDailyRecommendCount(page));
            page.setRows(NoticeDao.getDailyRecommendList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addRecommend(DailyRecommend recommend) {
        try{
            NoticeDao.addRecommendText(recommend);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateRecommend(DailyRecommend recommend) {
        try{
            NoticeDao.updateRecommendText(recommend);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteRecommend(List<Long> recommendIds) {
        try{
            NoticeDao.deleteRecommendText(recommendIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getCategoryList(PageBean page) {
        try{
            page.setTotal(NoticeDao.getRecommendCategoryCount(page));
            page.setRows(NoticeDao.getRecommendCategoryList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addCategory(RecommendCategory category) {
        try{
            NoticeDao.addRecommendCategory(category);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateCategory(RecommendCategory category) {
        try{
            NoticeDao.updateRecommendCategory(category);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteCategory(List<Long> categoryIds) {
        try{
            NoticeDao.deleteRecommendCategory(categoryIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }
}
