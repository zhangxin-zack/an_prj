package com.scorer.client.controller;

import com.scorer.client.entity.Notice;
import com.scorer.client.service.NoticeService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Scorer/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/get")
    public Map getNoticeById(@RequestBody Integer id) {
        return noticeService.getNoticeById(id);
    }

    @RequestMapping(value = "/list")
    public Map getNoticeList(@RequestBody PageBean page) {
        return noticeService.getNoticeList(page);
    }

    @RequestMapping(value = "/add")
    public Map addNotice(@RequestBody Notice Notice) {
        return noticeService.addNotice(Notice);
    }

    @RequestMapping(value = "/update")
    public Map updateNotice(@RequestBody Notice Notice) {
        return noticeService.updateNotice(Notice);
    }

    @RequestMapping(value = "/remove")
    public Map deleteNotice(@RequestBody List<Integer> NoticeIds) {
        return noticeService.deleteNotice(NoticeIds);
    }

}