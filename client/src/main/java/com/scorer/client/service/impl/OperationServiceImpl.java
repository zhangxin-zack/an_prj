package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ClassesDao;
import com.scorer.client.dao.mysql_dao1.OperationDao;
import com.scorer.client.dao.mysql_dao1.StudentDao;
import com.scorer.client.dao.mysql_dao1.TeacherDao;
import com.scorer.client.entity.*;
import com.scorer.client.service.ClassesService;
import com.scorer.client.service.OperationService;
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
    public Map<String, Object> addIntegrationRule(IntegrationRule rule) {
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
}
