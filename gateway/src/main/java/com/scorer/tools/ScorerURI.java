package com.scorer.tools;


import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ScorerURI {

    public static Set<String> NeedAppCheckURI=new HashSet<>();
    public static Set<String> NeedAppCheckAppToken=new HashSet<>();
    public static Set<String> NeedAppCheckScorerAdminToken =new HashSet<>();

    static {
        initNeedAppCheckURI();
        initNeedAppCheckAppToken();
        initNeedAppCheckScorerAdminToken();
    }

    private static void initNeedAppCheckScorerAdminToken(){
        NeedAppCheckScorerAdminToken.add(ScorerAdmin.GetZoneArray);
    }

    private static void initNeedAppCheckURI(){
        setAllURI(NeedAppCheckURI,ScorerAppGroup.class);
    }

    private static void initNeedAppCheckAppToken(){
        setAllURI(NeedAppCheckAppToken,ScorerAppGroup.class);
        {
            NeedAppCheckAppToken.add(ScorerAppMainPageV3.MainPageSelf);
            NeedAppCheckAppToken.add(ScorerAppMainPageV3.MainPageManagerZone);
            NeedAppCheckAppToken.add(ScorerAppMainPageV3.MainPageManagerMatch);
        }
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

    public static class ScorerAdmin {
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

    public static class ScorerAppGroup {
        public static final String GetRoundList = "/Scorer/Group/GetRoundList";
        public static final String GetRoundInfo = "/Scorer/Group/GetRoundInfo";
        public static final String CreateRound = "/Scorer/Group/CreateRound";
        public static final String ModifyRound = "/Scorer/Group/ModifyRound";
        public static final String DeleteRound = "/Scorer/Group/DeleteRound";
        public static final String GetGroupInfoByGroupIdWithTeam = "/Scorer/Group/GetGroupInfoByGroupIdWithTeam";
        public static final String BeforeCreateGroupWithTeam = "/Scorer/Group/BeforeCreateGroupWithTeam";
        public static final String CreateGroupWithTeam = "/Scorer/Group/CreateGroupWithTeam";
        public static final String ModifyGroupWithTeam = "/Scorer/Group/ModifyGroupWithTeam";
        public static final String CreateGroup = "/Scorer/Group/CreateGroup";
        public static final String GetGroupInfoByGroupId = "/Scorer/Group/GetGroupInfoByGroupId";
        public static final String GetGroupList = "/Scorer/Group/GetGroupList";
        public static final String ModifyGroup = "/Scorer/Group/ModifyGroup";
        public static final String DeleteGroup = "/Scorer/Group/DeleteGroup";
        public static final String AddTeamsToGroup = "/Scorer/Group/AddTeamsToGroup";
        public static final String DeleteTeamsFromGroup = "/Scorer/Group/DeleteTeamsFromGroup";
        public static final String GetTeamsInfoByZoneId_UnGroup = "/Scorer/Group/GetTeamsInfoByZoneId_UnGroup";
        public static final String GetTeamsInfoByZoneId = "/Scorer/Group/GetTeamsInfoByZoneId";
        public static final String GetTeamsInfoByGroupId = "/Scorer/Group/GetTeamsInfoByGroupId";
        public static final String GetSeasonShowByGroupId = "/Scorer/Group/GetSeasonShowByGroupId";
        public static final String DeleteSeasonFromGroup = "/Scorer/Group/DeleteSeasonFromGroup";
        public static final String OneKeyToCreate = "/Scorer/Group/OneKeyToCreate";
        public static final String CreateSeasonBatch = "/Scorer/Group/CreateSeasonBatch";
    }


    public static class ScorerAppMainPageV3 {
        public static final String MainPageInZone = "/Scorer/MainPageV3/MainPageInZone";
        public static final String MainPageInMatch = "/Scorer/MainPageV3/MainPageInMatch";
        public static final String MainPageSelf = "/Scorer/MainPageV3/MainPageSelf";
        public static final String MainPageManagerZone = "/Scorer/MainPageV3/MainPageManagerZone";
        public static final String MainPageManagerMatch = "/Scorer/MainPageV3/MainPageManagerMatch";
    }


}
