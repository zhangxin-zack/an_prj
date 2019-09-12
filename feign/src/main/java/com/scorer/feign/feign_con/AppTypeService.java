package com.scorer.feign.feign_con;

import com.scorer.feign.entity.AppTypeDetail;
import com.scorer.feign.entity.AppTypeInfo;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "scorer-client")
public interface AppTypeService {

    @RequestMapping(value = "/EDU/APP/type/addAppTypeInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addAppTypeInfo(@RequestBody AppTypeInfo appTypeInfo);

    @RequestMapping(value = "/EDU/APP/type/delAppTypeInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map delAppTypeInfo(@RequestParam(value = "id") Integer id);

    @RequestMapping(value = "/EDU/APP/type/updateAppTypeInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateAppTypeInfo(@RequestBody AppTypeInfo appTypeInfo);

    @RequestMapping(value = "/EDU/APP/type/getAppTypeInfoList", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getAppTypeInfoList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/APP/type/addAppTypeDetail", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addAppTypeDetail(@RequestBody AppTypeDetail appTypeDetail);

    @RequestMapping(value = "/EDU/APP/type/delAppTypeDetail", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map delAppTypeDetail(@RequestParam(value = "id") Integer id);

    @RequestMapping(value = "/EDU/APP/type/updateAppTypeDetail", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateAppTypeDetail(@RequestBody AppTypeDetail appTypeDetail);

    @RequestMapping(value = "/EDU/APP/type/getAppTypeDetailList", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getAppTypeDetailList(@RequestBody PageBean page);
}
