package com.scorer.feign.feign_con;

import com.scorer.feign.entity.WSMessage;

import java.util.List;

public interface Customer_Service {
    List<WSMessage> GetAllUserUnreadMsg(Integer valueOf);

    void SetReadMessage(WSMessage message);
}
