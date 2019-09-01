package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ClassesDao;
import com.scorer.client.dao.mysql_dao1.SchoolDao;
import com.scorer.client.entity.School;
import com.scorer.client.entity.SchoolMenu;
import com.scorer.client.service.SchoolService;
import com.scorer.client.service.impl.BaseSeviceImpl;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolServiceImpl extends BaseSeviceImpl implements SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private ClassesDao classDao;

    @Override
    public Map<String, Object> getSchoolList(PageBean page) {
        try{
            page.setTotal(schoolDao.getSchoolCount(page));
            page.setRows(schoolDao.getSchoolList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addSchool(School School) {
        try{
            schoolDao.addSchool(School);
            //发送邮件服务 todo


            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateSchool(School School) {
        try{
            schoolDao.updateSchool(School);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteSchool(List SchoolIds) {
        try{
            schoolDao.deleteSchool(SchoolIds);
            //删除外键
            schoolDao.deleteSchoolFK(SchoolIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getSchoolById(School school) {
        try{
            List<School> scs = schoolDao.getSchoolByCondition(school);
            if(scs != null && scs.size() == 1){
                School sc = scs.get(0);
                //查询学校班级数
                sc.setClassCount(schoolDao.getClassCountBySchoolId(school.getId()));
                //查询学校总学生数
                sc.setStudentCount(schoolDao.getClassStudentCountBySchoolId(school.getId()));
                return resultMap(Iconstants.RESULT_CODE_0, "success", sc);
            }
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getSchoolByCondition(School school) {
        try{
            List<School> scs = schoolDao.getSchoolByCondition(school);
            return resultMap(Iconstants.RESULT_CODE_0, "success", scs);
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getSchoolDetail(PageBean page) {
        try{
            page.setTotal(schoolDao.getSchoolDetailCount(page));
            page.setRows(schoolDao.getSchoolDetailList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getSchoolReport(PageBean page) {
        try{
            page.setTotal(schoolDao.getSchoolReportCount(page));
            page.setRows(schoolDao.getSchoolReportList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map addSchoolMenu(SchoolMenu menu) {
        try{
            schoolDao.addSchoolMenu(menu);
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map updateSchoolMenu(SchoolMenu menu) {
        try{
            schoolDao.updateSchoolMenu(menu);
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map deleteSchoolMenu(List<Long> schoolMenuIds) {
        try{
            schoolDao.deleteSchoolMenus(schoolMenuIds);
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map getSchoolMenuList(PageBean page) {
        try{
            page.setTotal(schoolDao.getAllSchoolMenuListForPageCount(page));
            page.setRows(schoolDao.getAllSchoolMenuListForPage(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map getSchoolMenuTree() {
        try{
            List<SchoolMenu> menuList = schoolDao.getAllSchoolMenuList();
            return resultMap(Iconstants.RESULT_CODE_0, "success", getTreeDataForSchoolMenu(menuList));
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getClassCountBySchoolId(long schoolId) {
        try{
            long classCount = schoolDao.getClassCountBySchoolId(schoolId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", classCount);
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getClassStudentCountBySchoolId(long schoolId) {
        try{
            long classStudentCount = schoolDao.getClassCountBySchoolId(schoolId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", classStudentCount);
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    private Map<String, Object> getTreeDataForSchoolMenu(List<SchoolMenu> menuList){
        //获取根节点
        SchoolMenu rootMenu = null;
        for(SchoolMenu menu: menuList){
            long pid = menu.getMenuPid();
            if(pid == 0){
                rootMenu = menu;
            }
        }
        //拼接根节点数据
        Map<String, Object> datamap = new HashMap<>();
        long lv1Id = rootMenu.getMenuId();
        datamap.put("id", lv1Id);
        datamap.put("pid", rootMenu.getMenuPid());
        datamap.put("name", rootMenu.getMenuTitle());
        datamap.put("path", rootMenu.getPath());
        datamap.put("icon", rootMenu.getIcon());
        List<Map<String, Object>> child = new ArrayList<>();
        for(SchoolMenu menu: menuList){
            //递归拼接数据
            recursionDataForSchool(menuList, lv1Id, menu, child);
        }
        datamap.put("children", child);
        return datamap;
    }

    private void recursionDataForSchool(List<SchoolMenu> menus, long perId, SchoolMenu currentMenu,
                                        List<Map<String, Object>> perChild){
        long pid = currentMenu.getMenuPid();
        if(pid == perId){
            long currentId = currentMenu.getMenuId();
            Map<String, Object> data = new HashMap<>();
            data.put("id", currentId);
            data.put("pid", pid);
            data.put("name", currentMenu.getMenuTitle());
            data.put("path", currentMenu.getPath());
            data.put("icon", currentMenu.getIcon());
            List<Map<String, Object>> child = new ArrayList<>();
            for(SchoolMenu nextMenu: menus) {
                recursionDataForSchool(menus, currentId, nextMenu, child);
            }
            data.put("children", child);
            perChild.add(data);
        }
    }
}
