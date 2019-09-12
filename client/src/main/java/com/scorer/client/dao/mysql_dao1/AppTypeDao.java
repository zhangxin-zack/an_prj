package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.AppTypeDetail;
import com.scorer.client.entity.AppTypeInfo;
import com.scorer.client.values.PageBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface AppTypeDao {

    void addAppTypeInfo(AppTypeInfo appTypeInfo);

    void delAppTypeInfo(@Param("id") Integer id);

    void updateAppTypeInfo(AppTypeInfo appTypeInfo);

    List<AppTypeInfo> getAppTypeInfoList(PageBean page);

    Long getAppTypeInfoListCount(PageBean page);

    @Select("SELECT * FROM app_type_detail WHERE type_id=#{id}")
    List<AppTypeDetail> getAppTypeDetailById(@Param("id") Integer id);

    void addAppTypeDetail(AppTypeDetail appTypeDetail);

    void delAppTypeDetail(@Param("id") Integer id);

    void updateAppTypeDetail(AppTypeDetail appTypeDetail);

    Long getAppTypeDetailListCount(PageBean page);

    List<AppTypeDetail> getAppTypeDetailList(PageBean page);
}
