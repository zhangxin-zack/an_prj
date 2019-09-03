package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.WSMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao {
    void SaveMSG(WSMessage wsMessage);
}
