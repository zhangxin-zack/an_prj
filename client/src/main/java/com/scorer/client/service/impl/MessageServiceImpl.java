package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.AccountDao;
import com.scorer.client.dao.mysql_dao1.MessageDao;
import com.scorer.client.entity.Account;
import com.scorer.client.entity.WSMessage;
import com.scorer.client.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class MessageServiceImpl extends BaseSeviceImpl implements MessageService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private AccountDao accountDao;

    @Override
    public WSMessage SaveMSG(WSMessage wsMessage) {
        mongoTemplate.insert(wsMessage);
//        messageDao.SaveMSG(wsMessage);
        return wsMessage;
    }

    @Override
    public Map GetUserOneMessage(Integer uid) {
        Map<Integer, WSMessage> homeMsg = new HashMap<>();
        Map<Integer, WSMessage> classMsg = new HashMap<>();
        List<Integer> studentList = messageDao.GetStudentListBy(uid);
        List<Integer> classList = messageDao.GetClassListBy(uid);
        for (Integer student_id : studentList) {
            homeMsg.put(student_id,
                    mongoTemplate.findOne(
                    Query.query(Criteria
                            .where("to_home").is(1)
                            .and("from_student_id").is(student_id)
                    ).with(new Sort(Sort.Direction.DESC, "msg_time")).limit(1), WSMessage.class)
            );
        }
        for (Integer class_id : classList) {
            classMsg.put(class_id,
                    mongoTemplate.findOne(
                            Query.query(Criteria
                                    .where("to_home").is(-1)
                                    .and("to_class").is(class_id)
                            ).with(new Sort(Sort.Direction.DESC, "msg_time")).limit(1), WSMessage.class)
            );
        }
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("homeMsg",homeMsg);
        reMap.put("classMsg",classMsg);
        return resultMap(Iconstants.RESULT_CODE_0, "success", reMap);
    }

    @Override
    public Map GetHomeMSG(Long time, Integer count, Long student_id) {
//        List<WSMessage> wsMessageList = messageDao.GetHomeMSG(time, count, student_id);
        List<WSMessage> wsMessageList = mongoTemplate.find(
                Query.query(Criteria
                        .where("msg_time").lt(time)
                        .and("from_student_id").is(student_id)
                ).with(new Sort(Sort.Direction.DESC, "msg_time")).limit(count), WSMessage.class);
        return resultMap(Iconstants.RESULT_CODE_0, "success", wsMessageList);
    }

    @Override
    public Map GetClassMSG(Long time, Integer count, Long class_id) {
//        List<WSMessage> wsMessageList = messageDao.GetClassMSG(time, count, String.valueOf(class_id));
        List<WSMessage> wsMessageList = mongoTemplate.find(
                Query.query(Criteria
                        .where("msg_time").lt(time)
                        .and("to_class").is(class_id)
                ).with(new Sort(Sort.Direction.DESC, "msg_time")).limit(count), WSMessage.class);
        return resultMap(Iconstants.RESULT_CODE_0, "success", wsMessageList);
    }

    @Override
    public Map InviteUser(String nick_name, String phone, Long student_id) {
        try {
            Long uid = accountDao.getAccountIdByPhone(phone);
            if (uid == null || uid == 0) {
                String password = String.valueOf(new Random().nextInt(900000) + 100000);
                Account account = new Account(phone, password);
                accountDao.addAccount(account);
                uid = account.getId();
                //注册默认为家长
                accountDao.addAccountTitle(uid, 1L);
            }
            //发送短信
            messageDao.InviteUser(uid, student_id);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map ListHomeUser(Integer student_id) {
        List<Account> accountList = messageDao.ListHomeUser(student_id);
        return resultMap(Iconstants.RESULT_CODE_0, "success", accountList);
    }

    @Override
    public Map ListClassUser(Integer class_id) {
        List<Account> accountList = messageDao.ListClassUser(class_id);
        return resultMap(Iconstants.RESULT_CODE_0, "success", accountList);
    }

    @Override
    public Map KickUser(Integer uid, Integer student_id) {
        messageDao.KickUser(uid, student_id);
        return resultInfo(Iconstants.RESULT_CODE_0, "success");
    }


}
