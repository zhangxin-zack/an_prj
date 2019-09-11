package com.scorer.clientPhone.dao.mysql_dao1;

import com.scorer.clientPhone.entity.Student;
import com.scorer.clientPhone.netty.P_Message;

public interface PhoneDao {
    Student GetStudentInfoByRingNo(P_Message msg);
}
