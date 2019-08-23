package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Device;
import com.scorer.client.entity.IntegrationRule;
import com.scorer.client.entity.Product;
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

    Long getDeviceCount(PageBean page) throws Exception;

    List<Device> getDeviceList(PageBean page) throws Exception;

    void addDevice(Device device) throws Exception;

    void updateDevice(Device device) throws Exception;

    void deleteDevice(List<Long> deviceIds) throws Exception;

    Long getProductCount(PageBean page) throws Exception;

    List<Product> getProductList(PageBean page) throws Exception;

    void addProduct(Product product) throws Exception;

    void updateProduct(Product product) throws Exception;

    void deleteProduct(List<Long> ProductIds) throws Exception;

}