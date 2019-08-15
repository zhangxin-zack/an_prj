package com.scorer.client.service;

import com.scorer.client.entity.Account;
import com.scorer.client.entity.Classes;
import com.scorer.client.entity.Student;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;


public interface AccountService {

//    Map<String, Object> getClassesList(PageBean page);
//
//    Map<String, Object> getClassesById(Integer classesId);
//
//    Map<String, Object> addClasses(Classes classes);
//
//    Map<String, Object> updateClasses(Classes classes);
//
//    Map<String, Object> deleteClasses(List classesIds);

    Map<String, Object> getValidateCode(String phone);

    Map<String, Object> register(Account account);

    Map<String, Object> login(Account account);

    Map<String, Object> updatePassword(Account account);

    Map<String, Object> resetPassword(Account account);

    Map<String, Object> updateAccount(Account account);

    Map<String, Object> addBaby(Student student);

    Map<String, Object> updateBaby(Student student);

    Map<String, Object> listBaby(PageBean page);
}
