package com.scorer.client.controller;

import com.scorer.client.entity.Notice;
import com.scorer.client.service.NoticeService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/get")
    public Map getNoticeById(Integer id) {
        return noticeService.getNoticeById(id);
    }

    @RequestMapping(value = "/list")
    public Map getNoticeList(PageBean page) {
        return noticeService.getNoticeList(page);
    }

    @RequestMapping(value = "/add")
    public Map addNotice(Notice Notice) {
        return noticeService.addNotice(Notice);
    }

    @RequestMapping(value = "/update")
    public Map updateNotice(Notice Notice) {
        return noticeService.updateNotice(Notice);
    }

    @RequestMapping(value = "/remove")
    public Map deleteNotice(List<Integer> NoticeIds) {
        return noticeService.deleteNotice(NoticeIds);
    }

}