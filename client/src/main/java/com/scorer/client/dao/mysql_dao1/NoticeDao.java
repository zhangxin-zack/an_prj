package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.DailyRecommend;
import com.scorer.client.entity.Notice;
import com.scorer.client.entity.NoticeId;
import com.scorer.client.entity.RecommendCategory;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDao {

    Long getNoticeCount(PageBean page) throws Exception;

    List<Notice> getNoticeListBK(PageBean page) throws Exception;

    Notice getNoticeById(Integer noticeId) throws Exception;

    void addNotice(Notice notice) throws Exception;

    void updateNotice(Notice notice) throws Exception;

    void deleteNotice(List noticeIds) throws Exception;

    Long getDailyRecommendCount(PageBean page) throws Exception;

    List<DailyRecommend> getDailyRecommendList(PageBean page) throws Exception;

    void addRecommendText(DailyRecommend text) throws Exception;

    void updateRecommendText(DailyRecommend text) throws Exception;

    void deleteRecommendText(List textIds) throws Exception;

    Long getRecommendCategoryCount(PageBean page) throws Exception;

    List<RecommendCategory> getRecommendCategoryList(PageBean page) throws Exception;

    void addRecommendCategory(RecommendCategory category) throws Exception;

    void updateRecommendCategory(RecommendCategory category) throws Exception;

    void deleteRecommendCategory(List categoryIds) throws Exception;

    void beforeAddNotice(NoticeId noticeId);

    List getNoticeListPhone(PageBean page);

    Long getNoticeCountPhone(PageBean page);
}
