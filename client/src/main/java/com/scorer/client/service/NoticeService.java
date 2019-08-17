package com.scorer.client.service;

import com.scorer.client.entity.DailyRecommend;
import com.scorer.client.entity.Notice;
import com.scorer.client.values.PageBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    Map<String, Object> getNoticeById(Integer Notice);

    Map<String, Object> getNoticeList(PageBean page);

    Map<String, Object> addNotice(Notice notice);

    Map<String, Object> updateNotice(Notice notice);

    Map<String, Object> deleteNotice(List noticeIds);

    Map<String, Object> getDailyInfoList(PageBean page);

    Map<String, Object> addRecommend(DailyRecommend recommend);

    Map<String, Object> updateRecommend(DailyRecommend recommend);

    Map<String, Object> deleteRecommend(List<Long> recommendIds);
}
