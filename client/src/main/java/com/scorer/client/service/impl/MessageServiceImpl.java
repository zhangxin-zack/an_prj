package com.scorer.client.service.impl;

import com.scorer.client.dao.mysql_dao1.MessageDao;
import com.scorer.client.entity.WSMessage;
import com.scorer.client.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public WSMessage SaveMSG(WSMessage wsMessage) {
        messageDao.SaveMSG(wsMessage);
        return wsMessage;
    }
}
