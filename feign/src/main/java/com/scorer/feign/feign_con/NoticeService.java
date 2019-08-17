package com.scorer.feign.feign_con;

import com.scorer.feign.entity.DailyRecommend;
import com.scorer.feign.entity.Notice;
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

    @RequestMapping(value = "/EDU/notice/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addNotice(@RequestBody Notice notice);

    @RequestMapping(value = "/EDU/notice/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateNotice(@RequestBody Notice notice);

    @RequestMapping(value = "/EDU/notice/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteNotice(@RequestParam(value = "noticeIds") List<Integer> noticeIds);

    @RequestMapping(value = "/EDU/notice/list_daily_recommend", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getDailyInfoList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/notice/add_recommend")
    Map addRecommend(@RequestBody DailyRecommend recommend);

    @RequestMapping(value = "/EDU/notice/update_recommend")
    Map updateRecommend(@RequestBody DailyRecommend recommend);

    @RequestMapping(value = "/EDU/notice/remove_recommend")
    Map deleteRecommend(@RequestParam("recommendIds") List<Long> recommendIds);
}
