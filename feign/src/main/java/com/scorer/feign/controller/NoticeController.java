package com.scorer.feign.controller;

import com.scorer.feign.entity.DailyRecommend;
import com.scorer.feign.entity.Notice;
import com.scorer.feign.entity.RecommendCategory;
import com.scorer.feign.feign_con.NoticeService;
import com.scorer.feign.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/list/Phone")
    public Map getNoticeListPhone(@RequestBody PageBean page) {
        return noticeService.getNoticeListPhone(page);
    }

    @RequestMapping(value = "/add")
    public Map addNotice(@RequestParam("noticeTitle") String noticeTitle,
                         @RequestParam("noticeContent") String noticeContent,
                         @RequestPart(value = "noticeFile") MultipartFile noticeFile,
                         @RequestParam("classIds") List<String> classIds,
                         @RequestParam("fromTo") Integer fromTo) {
        return noticeService.addNotice(noticeTitle, noticeContent, noticeFile, classIds, fromTo);
    }

    @RequestMapping(value = "/addSort")
    public Map addNoticeSort(@RequestParam("noticeTitle") String noticeTitle,
                             @RequestParam("noticeContent") String noticeContent,
                             @RequestParam("classIds") List<String> classIds,
                             @RequestParam("fromTo") Integer fromTo) {
        return noticeService.addNoticeSort(noticeTitle, noticeContent, classIds, fromTo);
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

    @RequestMapping(value = "/list_recommend_category")
    public Map getCategory(@RequestBody PageBean page) {
        return noticeService.getCategoryList(page);
    }

    @RequestMapping(value = "/add_recommend_category")
    public Map addCategory(@RequestBody RecommendCategory category) {
        return noticeService.addCategory(category);
    }

    @RequestMapping(value = "/update_recommend_category")
    public Map updateCategory(@RequestBody RecommendCategory category) {
        return noticeService.updateCategory(category);
    }

    @RequestMapping(value = "/remove_recommend_category")
    public Map deleteCategory(@RequestParam("categoryIds") List<Long> categoryIds) {
        return noticeService.deleteCategory(categoryIds);
    }
}