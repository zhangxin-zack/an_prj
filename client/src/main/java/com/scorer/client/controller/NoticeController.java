package com.scorer.client.controller;

import com.scorer.client.entity.DailyRecommend;
import com.scorer.client.entity.Notice;
import com.scorer.client.service.NoticeService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/get")
    public Map getNoticeById(@RequestParam("id") Integer id) {
        return noticeService.getNoticeById(id);
    }

    @RequestMapping(value = "/list")
    public Map getNoticeList(@RequestBody PageBean page) {
        return noticeService.getNoticeList(page);
    }

    @RequestMapping(value = "/add")
    public Map addNotice(@RequestBody Notice notice) {
        return noticeService.addNotice(notice);
    }

    @RequestMapping(value = "/update")
    public Map updateNotice(@RequestBody Notice notice) {
        return noticeService.updateNotice(notice);
    }

    @RequestMapping(value = "/remove")
    public Map deleteNotice(@RequestParam("noticeIds") List<Integer> noticeIds) {
        return noticeService.deleteNotice(noticeIds);
    }

    @RequestMapping(value = "/list_daily_recommend")
    public Map getDailyInfo(@RequestBody PageBean page) {
        return noticeService.getDailyInfoList(page);
    }

    @RequestMapping(value = "/add_recommend")
    public Map addRecommend(@RequestBody DailyRecommend recommend) {
        return noticeService.addRecommend(recommend);
    }

    @RequestMapping(value = "/update_recommend")
    public Map updateRecommend(@RequestBody DailyRecommend recommend) {
        return noticeService.updateRecommend(recommend);
    }

    @RequestMapping(value = "/remove_recommend")
    public Map deleteRecommend(@RequestParam("recommendIds") List<Long> recommendIds) {
        return noticeService.deleteRecommend(recommendIds);
    }
}