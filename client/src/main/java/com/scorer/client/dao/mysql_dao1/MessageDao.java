package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Account;
import com.scorer.client.entity.WSMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
    void SaveMSG(WSMessage wsMessage);

    List<WSMessage> GetHomeMSG(@Param("time") Long time,
                               @Param("count") Integer count,
                               @Param("student_id") Long student_id);

    List<WSMessage> GetClassMSG(@Param("time") Long time,
                                @Param("count") Integer count,
                                @Param("class_id")  String class_id);

    List<Account> ListHomeUser(@Param("student_id") Integer student_id);

    List<Account> ListClassUser(@Param("class_id") Integer class_id);

    void KickUser(Integer uid, Integer student_id);

    void InviteUser(Long uid, Long student_id);

    @Select("SELECT student_id FROM account_student WHERE account_id=#{uid}")
    List<Integer> GetStudentListBy(@Param("uid") Integer uid);

    @Select("SELECT class_id FROM students WHERE account_id=#{uid} AND class_id IS NOT NULL")
    List<Integer> GetClassListBy(@Param("uid") Integer uid);
}
