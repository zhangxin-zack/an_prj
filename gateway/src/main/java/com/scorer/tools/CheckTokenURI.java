package com.scorer.tools;


import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ALL")
public class CheckTokenURI {

    public static Set<String> NeedAppRSAToken =new HashSet<>();
    public static Set<String> NeedAppUserToken =new HashSet<>();
    public static Set<String> NeedWebManagerToken =new HashSet<>();

    static {
        initNeedCheckAppRSAToken();
        initNeedCheckAppUserToken();
        initNeedCheckWebManageToken();
    }

    private static void initNeedCheckWebManageToken(){
        setAllURI(NeedWebManagerToken,EDUWebManage.class);
    }

    private static void initNeedCheckAppRSAToken(){
    }

    private static void initNeedCheckAppUserToken(){
        setAllURI(NeedAppUserToken,EDUAPPUser.class);
    }

    private static <T> void setAllURI(Set<String> inSet,Class<T> tClass){
        Field[] fields = tClass.getDeclaredFields();
        for(Field field:fields){
            try {
                inSet.add((String) field.get(null));
            } catch (IllegalAccessException ignored) {
            }
        }
    }

    public static class EDUAPPUser {
        public static final String Account_update_password = "/EDU/account/update_password";
        public static final String Account_update_reset_pwd = "/EDU/account/update_reset_pwd";
        public static final String Account_update = "/EDU/account/update";
        public static final String Account_add_baby = "/EDU/account/add_baby";
        public static final String Account_update_baby = "/EDU/account/update_baby";

    }

    public static class EDUWebManage {
        public static final String Student_BK_list = "/EDU/student/bk/list";
        public static final String Teacher_list = "/EDU/teacher/list";
        public static final String Teacher_get = "/EDU/teacher/get";
        public static final String Teacher_add = "/EDU/teacher/add";
        public static final String Teacher_update = "/EDU/teacher/update";
        public static final String Teacher_remove = "/EDU/teacher/remove";

    }



}
