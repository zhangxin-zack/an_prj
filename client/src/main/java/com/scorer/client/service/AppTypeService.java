package com.scorer.client.service;

import com.scorer.client.entity.AppTypeDetail;
import com.scorer.client.entity.AppTypeInfo;
import com.scorer.client.values.PageBean;

import java.util.Map;

public interface AppTypeService {
    Map addAppTypeInfo(AppTypeInfo appTypeInfo);

    Map delAppTypeInfo(Integer id);

    Map updateAppTypeInfo(AppTypeInfo appTypeInfo);

    Map getAppTypeInfoList(PageBean page);

    Map addAppTypeDetail(AppTypeDetail appTypeDetail);

    Map delAppTypeDetail(Integer id);

    Map updateAppTypeDetail(AppTypeDetail appTypeDetail);

    Map getAppTypeDetailList(PageBean page);
}
