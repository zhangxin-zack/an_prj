package com.scorer.feign.controller;

import com.scorer.feign.entity.Notice;
import com.scorer.feign.feign_con.NoticeService;
import com.scorer.feign.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @RequestMapping(value = "/get")
    public Map getNoticeById(@RequestParam(value = "id") Integer id) {
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

    @RequestMapping(value = {"/list_daily_recommend", "/list_child_knowledge"})
    public Map getDailyInfo(@RequestBody PageBean page) {
        return noticeService.getDailyInfoList(page);
    }
}