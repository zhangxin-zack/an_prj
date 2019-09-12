package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.AppTypeDao;
import com.scorer.client.entity.AppTypeDetail;
import com.scorer.client.entity.AppTypeInfo;
import com.scorer.client.service.AppTypeService;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("appTypeService")
public class AppTypeServiceImpl extends BaseSeviceImpl implements AppTypeService {

    @Resource
    private AppTypeDao appTypeDao;

    @Override
    public Map addAppTypeInfo(AppTypeInfo appTypeInfo) {
        try {
            appTypeDao.addAppTypeInfo(appTypeInfo);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map delAppTypeInfo(Integer id) {
        try {
            appTypeDao.delAppTypeInfo(id);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map updateAppTypeInfo(AppTypeInfo appTypeInfo) {
        try {
            appTypeDao.updateAppTypeInfo(appTypeInfo);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map getAppTypeInfoList(PageBean page) {
        try {
            page.setTotal(appTypeDao.getAppTypeInfoListCount(page));
            List<AppTypeInfo> appTypeInfoList = appTypeDao.getAppTypeInfoList(page);
            for(AppTypeInfo appTypeInfo:appTypeInfoList){
                appTypeInfo.setAppTypeDetail(appTypeDao.getAppTypeDetailById(appTypeInfo.getId()));
            }
            page.setRows(appTypeInfoList);
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map addAppTypeDetail(AppTypeDetail appTypeDetail) {
        try {
            appTypeDao.addAppTypeDetail(appTypeDetail);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map delAppTypeDetail(Integer id) {
        try {
            appTypeDao.delAppTypeDetail(id);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map updateAppTypeDetail(AppTypeDetail appTypeDetail) {
        try {
            appTypeDao.updateAppTypeDetail(appTypeDetail);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map getAppTypeDetailList(PageBean page) {
        try {
            page.setTotal(appTypeDao.getAppTypeDetailListCount(page));
            page.setRows(appTypeDao.getAppTypeDetailList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }


}
