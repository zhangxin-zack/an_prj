package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.IntegrationRule;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationDao {

    Long getRuleCount(PageBean page) throws Exception;

    List<IntegrationRule> getRuleList(PageBean page) throws Exception;

    void addRule(IntegrationRule rule) throws Exception;

    void updateRule(IntegrationRule rule) throws Exception;

    void deleteRule(List<Long> ruleIds) throws Exception;


}