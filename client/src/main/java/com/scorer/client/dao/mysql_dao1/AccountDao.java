package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Account;
import com.scorer.client.entity.AppMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao {

    Account accountLogin(Account account) throws Exception;

    void addAccount(Account account) throws Exception;

    int updateAccount(Account account) throws Exception;

    void addAccountTitle(Long accountId, Long titleId) throws Exception;

    List<Long> getAccountAllTitle(Long accountId) throws Exception;

    List<AppMenu> getAllAppMenuList() throws Exception;

    List<AppMenu> getAppMenuList(Long accountId) throws Exception;
}