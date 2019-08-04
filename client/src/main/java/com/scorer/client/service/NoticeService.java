package com.scorer.client.service;

import com.scorer.client.entity.Notice;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    public Map<String, Object> getNoticeById(Integer Notice);

    public Map<String, Object> getNoticeList(PageBean page);

    public Map<String, Object> addNotice(Notice Notice);

    public Map<String, Object> updateNotice(Notice Notice);

    public Map<String, Object> deleteNotice(List NoticeIds);
}
