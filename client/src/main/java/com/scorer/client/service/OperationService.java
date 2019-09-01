package com.scorer.client.service;

import com.scorer.client.entity.Device;
import com.scorer.client.entity.IntegrationRule;
import com.scorer.client.entity.Product;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;


public interface OperationService {

    Map<String, Object> getIntegrationRuleList(PageBean page);

    Map<String, Object> addIntegrationRule(IntegrationRule rule);

    Map<String, Object> updateIntegrationRule(IntegrationRule rule);

    Map<String, Object> deleteIntegrationRule(List ruleIds);

    Map<String, Object> getDeviceList(PageBean page);

    Map<String, Object> addDevice(Device device);

    Map<String, Object> updateDevice(Device device);

    Map<String, Object> deleteDevice(List deviceIds);

    Map<String, Object> getProductList(PageBean page);

    Map<String, Object> addProduct(Product product);

    Map<String, Object> updateProduct(Product product);

    Map<String, Object> deleteProduct(List productIds);
}

