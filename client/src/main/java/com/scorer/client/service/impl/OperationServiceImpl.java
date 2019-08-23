package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.OperationDao;
import com.scorer.client.entity.IntegrationRule;
import com.scorer.client.service.OperationService;
import com.scorer.client.service.impl.BaseSeviceImpl;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OperationServiceImpl extends BaseSeviceImpl implements OperationService {

    @Autowired
    private OperationDao operationDao;

    @Override
    public Map<String, Object> getIntegrationRuleList(PageBean page) {
        try {
            page.setTotal(operationDao.getRuleCount(page));
            page.setRows(operationDao.getRuleList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addIntegrationRule(com.scorer.client.entity.IntegrationRule rule) {
        try {
            operationDao.addRule(rule);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateIntegrationRule(IntegrationRule rule) {
        try {
            operationDao.updateRule(rule);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteIntegrationRule(List ruleIds) {
        try {
            operationDao.deleteRule(ruleIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getDeviceList(PageBean page) {
        try {
            page.setTotal(operationDao.getDeviceCount(page));
            page.setRows(operationDao.getDeviceList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addDevice(com.scorer.client.entity.Device device) {
        try {
            operationDao.addDevice(device);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateDevice(com.scorer.client.entity.Device device) {
        try {
            operationDao.updateDevice(device);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteDevice(List deviceIds) {
        try {
            operationDao.deleteDevice(deviceIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }


    @Override
    public Map<String, Object> getProductList(PageBean page) {
        try {
            page.setTotal(operationDao.getProductCount(page));
            page.setRows(operationDao.getProductList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addProduct(com.scorer.client.entity.Product product) {
        try {
            operationDao.addProduct(product);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateProduct(com.scorer.client.entity.Product product) {
        try {
            operationDao.updateProduct(product);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteProduct(List productIds) {
        try {
            operationDao.deleteProduct(productIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }
}
