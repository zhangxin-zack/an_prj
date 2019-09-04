package com.scorer.client.service;

import com.scorer.client.entity.WSMessage;

import java.util.Map;

public interface MessageService {
    WSMessage SaveMSG(WSMessage wsMessage);

    Map GetHomeMSG(Long time, Integer count, Long student_id);

    Map GetClassMSG(Long time, Integer count, Long class_id);

    Map InviteUser(String nick_name, String phone, Long student_id);

    Map ListHomeUser(Integer student_id);

    Map ListClassUser(Integer class_id);

    Map KickUser(Integer uid, Integer student_id);
}
