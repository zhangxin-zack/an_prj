package com.scorer.feign.controller;

import com.scorer.feign.entity.AppTypeDetail;
import com.scorer.feign.entity.AppTypeInfo;
import com.scorer.feign.feign_con.AppTypeService;
import com.scorer.feign.values.PageBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/EDU/APP/type")
public class AppTypeController {

    @Resource
    private AppTypeService appTypeService;

    @RequestMapping(value = "/addAppTypeInfo")
    public Map addAppTypeInfo(@RequestBody AppTypeInfo appTypeInfo) {
        return appTypeService.addAppTypeInfo(appTypeInfo);
    }

    @RequestMapping(value = "/delAppTypeInfo")
    public Map delAppTypeInfo(@RequestParam(value = "id") Integer id) {
        return appTypeService.delAppTypeInfo(id);
    }

    @RequestMapping(value = "/updateAppTypeInfo")
    public Map updateAppTypeInfo(@RequestBody AppTypeInfo appTypeInfo) {
        return appTypeService.updateAppTypeInfo(appTypeInfo);
    }

    @RequestMapping(value = "/getAppTypeInfoList")
    public Map getAppTypeInfoList(@RequestBody PageBean page) {
        return appTypeService.getAppTypeInfoList(page);
    }

    @RequestMapping(value = "/addAppTypeDetail")
    public Map addAppTypeDetail(@RequestBody AppTypeDetail appTypeDetail) {
        return appTypeService.addAppTypeDetail(appTypeDetail);
    }

    @RequestMapping(value = "/delAppTypeDetail")
    public Map delAppTypeDetail(@RequestParam(value = "id") Integer id) {
        return appTypeService.delAppTypeDetail(id);
    }

    @RequestMapping(value = "/updateAppTypeDetail")
    public Map updateAppTypeDetail(@RequestBody AppTypeDetail appTypeDetail) {
        return appTypeService.updateAppTypeDetail(appTypeDetail);
    }

    @RequestMapping(value = "/getAppTypeDetailList")
    public Map getAppTypeDetailList(@RequestBody PageBean page) {
        return appTypeService.getAppTypeDetailList(page);
    }

}
