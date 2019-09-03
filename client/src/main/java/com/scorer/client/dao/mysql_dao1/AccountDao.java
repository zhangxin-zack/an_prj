package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Account;
import com.scorer.client.entity.AppMenu;
import com.scorer.client.values.PageBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountDao {

    Long getAccountCount(PageBean page) throws Exception;

    List<Account> getAccountList(PageBean page) throws Exception;

    Account accountLogin(Account account) throws Exception;

    void addAccount(Account account) throws Exception;

    int updateAccount(Account account) throws Exception;

    void addAccountTitle(Long accountId, Long titleId) throws Exception;

    List<Long> getAccountAllTitle(Long accountId) throws Exception;

    List<AppMenu> getAllAppMenuList() throws Exception;

    List<AppMenu> getAppMenuList(Long accountId) throws Exception;

    @Select("SELECT id FROM account WHERE phone=#{phone}")
    Long getAccountIdByPhone(@Param("phone") String phone);

    void deleteAccountTitle(List<Long> accountIds) throws Exception;

    List<Map> selectAccountBabyRelation(@Param("accountId") long accountId) throws Exception;

    List<Map> selectAccountClassRelation(@Param("accountId") long accountId) throws Exception;

    Long getAgentCount(PageBean page) throws Exception;

    List<Account> getAgentList(PageBean page) throws Exception;
}