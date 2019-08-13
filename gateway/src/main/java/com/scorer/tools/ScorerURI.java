package com.scorer.tools;


import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ScorerURI {

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
        public static final String Login = "/Scorer/Admin/Login";
        public static final String GetZoneArray = "/Scorer/Admin/GetZoneArray";
        public static final String GetSeasonShowsList = "/Scorer/Admin/GetSeasonShowsList";
        public static final String DeleteZoneByZoneId = "/Scorer/Admin/DeleteZoneByZoneId";
        public static final String UpdateZoneInfo = "/Scorer/Admin/UpdateZoneInfo";
        public static final String CreateZone = "/Scorer/Admin/CreateZone";
        public static final String AddSeasonToZone = "/Scorer/Admin/AddSeasonToZone";
        public static final String DelSeasonFromZone = "/Scorer/Admin/DelSeasonFromZone";
        public static final String GetZoneInfoByZoneId = "/Scorer/Admin/GetZoneInfoByZoneId";
        public static final String GetSeasonResultBySeasonId = "/Scorer/Admin/GetSeasonResultBySeasonId";
        public static final String UpdateSeasonResult = "/Scorer/Admin/UpdateSeasonResult";
        public static final String Logout = "/Scorer/Admin/Logout";
        public static final String GetZoneInfoByName = "/Scorer/Admin/GetZoneInfoByName";
        public static final String DelSeason = "/Scorer/Admin/DelSeason";
        public static final String ManualGameOverSeason = "/Scorer/Admin/ManualGameOverSeason";
        public static final String MergeZone = "/Scorer/Admin/MergeZone";
    }

    public static class EDUWebManage {
        public static final String Login = "/Scorer/Admin/Login";
        public static final String GetZoneArray = "/Scorer/Admin/GetZoneArray";
        public static final String GetSeasonShowsList = "/Scorer/Admin/GetSeasonShowsList";
        public static final String DeleteZoneByZoneId = "/Scorer/Admin/DeleteZoneByZoneId";
        public static final String UpdateZoneInfo = "/Scorer/Admin/UpdateZoneInfo";
        public static final String CreateZone = "/Scorer/Admin/CreateZone";
        public static final String AddSeasonToZone = "/Scorer/Admin/AddSeasonToZone";
        public static final String DelSeasonFromZone = "/Scorer/Admin/DelSeasonFromZone";
        public static final String GetZoneInfoByZoneId = "/Scorer/Admin/GetZoneInfoByZoneId";
        public static final String GetSeasonResultBySeasonId = "/Scorer/Admin/GetSeasonResultBySeasonId";
        public static final String UpdateSeasonResult = "/Scorer/Admin/UpdateSeasonResult";
        public static final String Logout = "/Scorer/Admin/Logout";
        public static final String GetZoneInfoByName = "/Scorer/Admin/GetZoneInfoByName";
        public static final String DelSeason = "/Scorer/Admin/DelSeason";
        public static final String ManualGameOverSeason = "/Scorer/Admin/ManualGameOverSeason";
        public static final String MergeZone = "/Scorer/Admin/MergeZone";
    }



}
