package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Notice;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDao {

    long getNoticeCount(PageBean page) throws Exception;

    List<Notice> getNoticeList(PageBean page) throws Exception;

    Notice getNoticeById(Integer noticeId) throws Exception;

    void addNotice(Notice notice) throws Exception;

    void updateNotice(Notice notice) throws Exception;

    void deleteNotice(List noticeIds) throws Exception;

}
