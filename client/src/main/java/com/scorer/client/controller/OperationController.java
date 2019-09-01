package com.scorer.client.controller;

import com.scorer.client.entity.IntegrationRule;
import com.scorer.client.service.OperationService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * app端用户相关接口
 */
@RestController
@RequestMapping("/EDU/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;


    /**
     * 修改积分规则
     * @param rule
     * @return
     */
    @RequestMapping(value = "/update_rule")
    public Map updateRule(@RequestBody com.scorer.client.entity.IntegrationRule rule) {
        return operationService.updateIntegrationRule(rule);
    }

    /**
     * 新增积分规则
     * @param rule
     * @return
     */
    @RequestMapping(value = "/add_rule")
    public Map addRule(@RequestBody IntegrationRule rule) {
        return operationService.addIntegrationRule(rule);
    }

    /**
     * 删除积分规则
     * @param ruleIds
     * @return
     */
    @RequestMapping(value = "/delete_rule")
    public Map deleteRule(@RequestParam(value = "ruleIds") List<Long> ruleIds) {
        return operationService.deleteIntegrationRule(ruleIds);
    }

    /**
     * 查询积分规则
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/list_rule")
    public Map listRule(@RequestBody PageBean pageBean) {
        return operationService.getIntegrationRuleList(pageBean);
    }


    /**
     * 修改设备
     * @param device
     * @return
     */
    @RequestMapping(value = "/update_device")
    public Map updateDevice(@RequestBody com.scorer.client.entity.Device device) {
        return operationService.updateDevice(device);
    }

    /**
     * 新增设备
     * @param device
     * @return
     */
    @RequestMapping(value = "/add_device")
    public Map addDevice(@RequestBody com.scorer.client.entity.Device device) {
        return operationService.addDevice(device);
    }

    /**
     * 删除设备
     * @param deviceIds
     * @return
     */
    @RequestMapping(value = "/delete_device")
    public Map deleteDevice(@RequestParam(value = "deviceIds") List<Long> deviceIds) {
        return operationService.deleteDevice(deviceIds);
    }

    /**
     * 查询设备
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/list_device")
    public Map listDevice(@RequestBody PageBean pageBean) {
        return operationService.getDeviceList(pageBean);
    }


    /**
     * 修改产品
     * @param Product
     * @return
     */
    @RequestMapping(value = "/update_product")
    public Map updateProduct(@RequestBody com.scorer.client.entity.Product Product) {
        return operationService.updateProduct(Product);
    }

    /**
     * 新增产品
     * @param Product
     * @return
     */
    @RequestMapping(value = "/add_product")
    public Map addProduct(@RequestBody com.scorer.client.entity.Product Product) {
        return operationService.addProduct(Product);
    }

    /**
     * 删除产品
     * @param ProductIds
     * @return
     */
    @RequestMapping(value = "/delete_product")
    public Map deleteProduct(@RequestParam(value = "productIds") List<Long> ProductIds) {
        return operationService.deleteProduct(ProductIds);
    }

    /**
     * 查询产品
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/list_product")
    public Map listProduct(@RequestBody PageBean pageBean) {
        return operationService.getProductList(pageBean);
    }

}
