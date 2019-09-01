package com.scorer.clientPhone.service.impl;

import com.scorer.clientPhone.dao.mysql_dao1.Request_Service_Dao;
import com.scorer.model.model1.Save_Request;
import com.scorer.clientPhone.service.Request_Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("request_service")
public class Request_Service_IMPL implements Request_Service {

    @Resource
    private Request_Service_Dao request_service_dao;

    @Override
    @Transactional
    public void saveRequest(Save_Request save_request) {
        request_service_dao.saveRequest(save_request);
    }
}
