package com.scorer.client.service;

import com.scorer.client.entity.Notice;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    Map<String, Object> getNoticeById(Integer Notice);

    Map<String, Object> getNoticeList(PageBean page);

    Map<String, Object> addNotice(Notice notice);

    Map<String, Object> updateNotice(Notice notice);

    Map<String, Object> deleteNotice(List noticeIds);

    Map<String, Object> getDailyInfoList(PageBean page);
}
