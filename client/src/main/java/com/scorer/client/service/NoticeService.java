package com.scorer.client.service;

import com.scorer.client.entity.Notice;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    Map<String, Object> getNoticeById(Integer Notice);

    Map<String, Object> getNoticeList(PageBean page);

    Map<String, Object> addNotice(Notice Notice);

    Map<String, Object> updateNotice(Notice Notice);

    Map<String, Object> deleteNotice(List NoticeIds);
}
