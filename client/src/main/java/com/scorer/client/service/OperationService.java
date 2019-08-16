package com.scorer.client.service;

import com.scorer.client.entity.IntegrationRule;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;


public interface OperationService {

    Map<String, Object> getIntegrationRuleList(PageBean page);

    Map<String, Object> addIntegrationRule(IntegrationRule rule);

    Map<String, Object> updateIntegrationRule(IntegrationRule rule);

    Map<String, Object> deleteIntegrationRule(List ruleIds);

}

