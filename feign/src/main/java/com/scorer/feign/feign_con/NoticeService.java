package com.scorer.feign.feign_con;

import com.scorer.feign.entity.DailyRecommend;
import com.scorer.feign.entity.Notice;
import com.scorer.feign.entity.RecommendCategory;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "scorer-client")
public interface NoticeService {
    @RequestMapping(value = "/EDU/notice/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getNoticeById(@RequestParam(value = "id") Integer id);

    @RequestMapping(value = "/EDU/notice/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getNoticeList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/notice/list/Phone", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getNoticeListPhone(PageBean page);

    @RequestMapping(value = "/EDU/notice/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addNotice(@RequestBody Notice notice);

    @RequestMapping(value = "/EDU/notice/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateNotice(@RequestBody Notice notice);

    @RequestMapping(value = "/EDU/notice/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteNotice(@RequestParam(value = "noticeIds") List<Integer> noticeIds);

    @RequestMapping(value = "/EDU/notice/list_daily_recommend", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getDailyInfoList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/notice/add_recommend", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addRecommend(@RequestBody DailyRecommend recommend);

    @RequestMapping(value = "/EDU/notice/update_recommend", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateRecommend(@RequestBody DailyRecommend recommend);

    @RequestMapping(value = "/EDU/notice/remove_recommend", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteRecommend(@RequestParam("recommendIds") List<Long> recommendIds);

    @RequestMapping(value = "/EDU/notice/list_recommend_category", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getCategoryList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/notice/add_recommend_category", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addCategory(@RequestBody RecommendCategory category);

    @RequestMapping(value = "/EDU/notice/update_recommend_category", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateCategory(@RequestBody RecommendCategory category);

    @RequestMapping(value = "/EDU/notice/remove_recommend_category", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteCategory(@RequestParam("recommendIds") List<Long> categoryIds);


}
