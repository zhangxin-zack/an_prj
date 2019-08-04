package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Notice;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDao {

    public long getNoticeCount(PageBean page) throws Exception;

    public List<Notice> getNoticeList(PageBean page) throws Exception;

    public Notice getNoticeById(Integer noticeId) throws Exception;

    public void addNotice(Notice notice) throws Exception;

    public void updateNotice(Notice notice) throws Exception;

    public void deleteNotice(List noticeIds) throws Exception;

}
