package com.scorer.feign.feign_con;

import com.scorer.feign.entity.Device;
import com.scorer.feign.entity.IntegrationRule;
import com.scorer.feign.entity.Product;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "scorer-client")
public interface OperationService {

    @RequestMapping(value = "/EDU/operation/list_rule", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> getIntegrationRuleList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/operation/add_rule", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> addIntegrationRule(@RequestBody IntegrationRule rule);

    @RequestMapping(value = "/EDU/operation/update_rule", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> updateIntegrationRule(@RequestBody IntegrationRule rule);

    @RequestMapping(value = "/EDU/operation/delete_rule", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> deleteIntegrationRule(@RequestParam(value = "ruleIds") List ruleIds);

    @RequestMapping(value = "/EDU/operation/list_device", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> getDeviceList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/operation/add_device", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> addDevice(@RequestBody Device device);

    @RequestMapping(value = "/EDU/operation/update_device", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> updateDevice(@RequestBody Device device);

    @RequestMapping(value = "/EDU/operation/delete_device", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> deleteDevice(@RequestParam(value = "deviceIds") List deviceIds);

    @RequestMapping(value = "/EDU/operation/list_product", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> getProductList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/operation/add_product", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> addProduct(@RequestBody Product product);

    @RequestMapping(value = "/EDU/operation/update_product", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> updateProduct(@RequestBody Product product);

    @RequestMapping(value = "/EDU/operation/delete_product", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> deleteProduct(@RequestParam(value = "productIds")  List productIds);
}

