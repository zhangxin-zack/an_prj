package com.scorer.clientPhone.service;

import com.scorer.clientPhone.netty.P_Message;
import io.netty.channel.Channel;

public interface PhoneSocketService {
    void ReceiveMSG(P_Message msg);

    void SendUnSettings(P_Message msg, Channel channel);
}
