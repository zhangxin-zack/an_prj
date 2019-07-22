package com.scorer.tools;

public class ScorerURI {

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

    public static class ScorerAppLogin {
        public static final String LoginWithWeChat = "/Scorer/Login/LoginWithWeChat";
        public static final String GetPhoneCode = "/Scorer/Login/GetPhoneCode";
        public static final String GetPhoneCode_Change = "/Scorer/Login/GetPhoneCode_Change";
        public static final String SavePhone = "/Scorer/Login/SavePhone";
        public static final String Logout = "/Scorer/Login/Logout";
        public static final String GetPhoneCodeLogin = "/Scorer/Login/GetPhoneCodeLogin";
        public static final String GetLoginWithPhone = "/Scorer/Login/GetLoginWithPhone";
        public static final String GetLoginWithPassword = "/Scorer/Login/GetLoginWithPassword";
        public static final String GetPasswordCode = "/Scorer/Login/GetPasswordCode";
        public static final String CheckPasswordCode = "/Scorer/Login/CheckPasswordCode";
        public static final String ChangePassword = "/Scorer/Login/ChangePassword";
        public static final String SaveUserPhone = "/Scorer/Login/SaveUserPhone";
    }

    public static class ScorerAppMainPage {
        public static final String GetMainPage = "/Scorer/MainPage/GetMainPage";
        public static final String GetMainPageV2 = "/Scorer/MainPage/GetMainPageV2";
        public static final String GetZoneList = "/Scorer/MainPage/GetZoneList";
        public static final String GetSeasonList = "/Scorer/MainPage/GetSeasonList";
        public static final String GetZoneListV2 = "/Scorer/MainPage/GetZoneListV2";
        public static final String GetSeasonShowListV2 = "/Scorer/MainPage/GetSeasonShowListV2";
    }

    public static class ScorerAppMainPageV3 {
        public static final String MainPageInZone = "/Scorer/MainPageV3/MainPageInZone";
        public static final String MainPageInMatch = "/Scorer/MainPageV3/MainPageInMatch";
        public static final String MainPageSelf = "/Scorer/MainPageV3/MainPageSelf";
        public static final String MainPageManagerZone = "/Scorer/MainPageV3/MainPageManagerZone";
        public static final String MainPageManagerMatch = "/Scorer/MainPageV3/MainPageManagerMatch";
    }

    public static class ScorerAppSeason {
        public static final String CreateSeason = "/Scorer/Season/CreateSeason";
        public static final String ShareSeason = "/Scorer/Season/ShareSeason";
        public static final String GetFieldNames = "/Scorer/Season/GetFieldNames";
        public static final String CheckSeason = "/Scorer/Season/CheckSeason";
        public static final String ReadySeason = "/Scorer/Season/ReadySeason";
        public static final String GetSeasonLaunch = "/Scorer/Season/GetSeasonLaunch";
        public static final String SaveSeasonStatus = "/Scorer/Season/SaveSeasonStatus";
        public static final String CancelSeasonStatus = "/Scorer/Season/CancelSeasonStatus";
        public static final String GetSeasonArray = "/Scorer/Season/GetSeasonArray";
        public static final String CheckJudgePhone = "/Scorer/Season/CheckJudgePhone";
        public static final String SaveSeasonRecorder = "/Scorer/Season/SaveSeasonRecorder";
        public static final String SaveMissedSeasonStatus = "/Scorer/Season/SaveMissedSeasonStatus";
        public static final String AddPlayerInGame = "/Scorer/Season/AddPlayerInGame";
        public static final String DownLoadRecorderIMG = "/Scorer/Season/DownLoadRecorderIMG";
        public static final String GetCurrentEncoder = "/Scorer/Season/GetCurrentEncoder";
        public static final String AddSeasonOrder = "/Scorer/Season/AddSeasonOrder";
        public static final String UpdateSeasonOrder = "/Scorer/Season/UpdateSeasonOrder";
        public static final String DelSeasonOrder = "/Scorer/Season/DelSeasonOrder";
        public static final String GetSeasonOrderByUid = "/Scorer/Season/GetSeasonOrderByUid";
        public static final String AddSeasonStatistics = "/Scorer/Season/AddSeasonStatistics";
        public static final String UpdateSeasonStatistics = "/Scorer/Season/UpdateSeasonStatistics";
        public static final String DelSeasonStatistics = "/Scorer/Season/DelSeasonStatistics";
        public static final String GetSeasonStatisticsByUid = "/Scorer/Season/GetSeasonStatisticsByUid";
        public static final String CreateReadySeason = "/Scorer/Season/CreateReadySeason";
        public static final String ModifySeasonExpectTime = "/Scorer/Season/ModifySeasonExpectTime";
        public static final String ModifySeasonStatistics = "/Scorer/Season/ModifySeasonStatistics";
        public static final String ModifySeasonOrder = "/Scorer/Season/ModifySeasonOrder";
        public static final String ModifySeasonField = "/Scorer/Season/ModifySeasonField";
    }

    public static class ScorerAppTV {
        public static final String SaveScorerInfo = "/Scorer/TV/SaveScorerInfo";
        public static final String GetSeasonListBySeasonId = "/Scorer/TV/GetSeasonListBySeasonId";
        public static final String GetCurrentKey = "/Scorer/TV/GetCurrentKey";
    }

    public static class ScorerAppUser {
        public static final String SaveUserInfo = "/Scorer/User/SaveUserInfo";
        public static final String GetUserInfo = "/Scorer/User/GetUserInfo";
        public static final String GetUserFeats = "/Scorer/User/GetUserFeats";
        public static final String GetUserPerformance = "/Scorer/User/GetUserPerformance";
        public static final String GetUserCareer = "/Scorer/User/GetUserCareer";
        public static final String GetUserCareerMax = "/Scorer/User/GetUserCareerMax";
        public static final String GetRanList = "/Scorer/User/GetRanList";
        public static final String FindUserList = "/Scorer/User/FindUserList";
        public static final String FindUserByPhone = "/Scorer/User/FindUserByPhone";
        public static final String AddUserInZone = "/Scorer/User/AddUserInZone";
    }

    public static class ScorerAppZone {
        public static final String GetZoneRanking = "/Scorer/Zone/GetZoneRanking";
        public static final String GetZoneInfoByZoneId = "/Scorer/Zone/GetZoneInfoByZoneId";
        public static final String GetSeasonInZone = "/Scorer/Zone/GetSeasonInZone";
        public static final String GetZoneScorerByZoneId = "/Scorer/Zone/GetZoneScorerByZoneId";
        public static final String CreateZone = "/Scorer/Zone/CreateZone";
        public static final String ModifyZone = "/Scorer/Zone/ModifyZone";
        public static final String GetMyZoneArray = "/Scorer/Zone/GetMyZoneArray";
        public static final String CreateSeasonInZone = "/Scorer/Zone/CreateSeasonInZone";
        public static final String FindRecorderUidByPhone = "/Scorer/Zone/FindRecorderUidByPhone";
        public static final String FindTeamsByTeamName = "/Scorer/Zone/FindTeamsByTeamName";
        public static final String FindTeamsInfoByZoneId = "/Scorer/Zone/FindTeamsInfoByZoneId";
        public static final String GetTeamsInfoByZoneId = "/Scorer/Zone/GetTeamsInfoByZoneId";
        public static final String CheckSeason = "/Scorer/Zone/CheckSeason";
        public static final String ChangeTeamLogo = "/Scorer/Zone/ChangeTeamLogo";
        public static final String CreateTeamInZone = "/Scorer/Zone/CreateTeamInZone";
        public static final String ModifyTeamInZone = "/Scorer/Zone/ModifyTeamInZone";
        public static final String DelTeamInZone = "/Scorer/Zone/DelTeamInZone";
        public static final String DelUserInZone = "/Scorer/Zone/DelUserInZone";
        public static final String InvitationManage = "/Scorer/Zone/InvitationManage";
        public static final String GetZoneManagerList = "/Scorer/Zone/GetZoneManagerList";
        public static final String DelZoneManager = "/Scorer/Zone/DelZoneManager";
        public static final String GetTeamInfoInZone = "/Scorer/Zone/GetTeamInfoInZone";
        public static final String AddPlayerToZoneTeam = "/Scorer/Zone/AddPlayerToZoneTeam";
    }

    public static class ScorerSDApp {
        public static final String FindUserUidByPhone = "/Scorer/SD/App/FindUserUidByPhone";
        public static final String CreateGroup = "/Scorer/SD/App/CreateGroup";
        public static final String GetGroupInfoByGroupId = "/Scorer/SD/App/GetGroupInfoByGroupId";
        public static final String ModifyGroup = "/Scorer/SD/App/ModifyGroup";
        public static final String GetGroupListInZone = "/Scorer/SD/App/GetGroupListInZone";
        public static final String GetQueueInZoneGroup = "/Scorer/SD/App/GetQueueInZoneGroup";
        public static final String DeleteTeamInQueue = "/Scorer/SD/App/DeleteTeamInQueue";
        public static final String MakeTeamTopInQueue = "/Scorer/SD/App/MakeTeamTopInQueue";
        public static final String ModifySeasonInQueue = "/Scorer/SD/App/ModifySeasonInQueue";
        public static final String GetSeasonInfoByGroupId = "/Scorer/SD/App/GetSeasonInfoByGroupId";
        public static final String DeleteSeason = "/Scorer/SD/App/DeleteSeason";
        public static final String ForceOverSeason = "/Scorer/SD/App/ForceOverSeason";
    }

    public static class ScorerSDHTML {
        public static final String SDUserCenter = "/Scorer/SD/HTML/SDUserCenter";
        public static final String SDGetMyQueueInfo = "/Scorer/SD/HTML/SDGetMyQueueInfo";
        public static final String SDGetInQueue = "/Scorer/SD/HTML/SDGetInQueue";
        public static final String SDGetTeamPointList = "/Scorer/SD/HTML/SDGetTeamPointList";
        public static final String SDGetTeamWinList = "/Scorer/SD/HTML/SDGetTeamWinList";
        public static final String SDGetUserPointList = "/Scorer/SD/HTML/SDGetUserPointList";
        public static final String SDGetTeamFourList = "/Scorer/SD/HTML/SDGetTeamFourList";
        public static final String SDGetGroupList = "/Scorer/SD/HTML/SDGetGroupList";
        public static final String SDGetSeasonListInGroup = "/Scorer/SD/HTML/SDGetSeasonListInGroup";
    }

    public static class ScorerSDThirdPart {
        public static final String AddUserBySDUserInfo = "/Scorer/ThirdPart/SD/AddUserBySDUserInfo";
        public static final String ChangeUserPhone = "/Scorer/ThirdPart/SD/ChangeUserPhone";
    }

    public static class ScorerHTMLLogin{
        public static final String LoginWithWeChat = "/Scorer/HTML/Login/LoginWithWeChat";
        public static final String GetPhoneCode = "/Scorer/HTML/Login/GetPhoneCode";
        public static final String GetPhoneCode_Change = "/Scorer/HTML/Login/GetPhoneCode_Change";
        public static final String CheckPhoneCode = "/Scorer/HTML/Login/CheckPhoneCode";
        public static final String GetUserInfo = "/Scorer/HTML/Login/GetUserInfo";
        public static final String SaveUserInfo = "/Scorer/HTML/Login/SaveUserInfo";
    }

    public static class ScorerHTMLMatch{
        public static final String GetMatchInfoFirst = "/Scorer/HTML/Match/GetMatchInfoFirst";
        public static final String SetMatchCountTime = "/Scorer/HTML/Match/SetMatchCountTime";
        public static final String StartMatchCountTime = "/Scorer/HTML/Match/StartMatchCountTime";
        public static final String StopMatchCountTime = "/Scorer/HTML/Match/StopMatchCountTime";
        public static final String SetMatchCoolTime = "/Scorer/HTML/Match/SetMatchCoolTime";
        public static final String StartMatchCoolTime = "/Scorer/HTML/Match/StartMatchCoolTime";
        public static final String StopMatchCoolTime = "/Scorer/HTML/Match/StopMatchCoolTime";
    }

    public static class ScorerHTMLSeason{
        public static final String JoinSeasonWeChat = "/Scorer/HTML/JoinSeasonWeChat";                      //需更新
        public static final String GetSeasonDetails = "/Scorer/HTML/GetSeasonDetails";                      //需更新
        public static final String GetSelectTeam = "/Scorer/HTML/GetSelectTeam";                            //需更新
        public static final String SaveUserSeasonCheck = "/Scorer/HTML/SaveUserSeasonCheck";                //需更新
        public static final String SavePhoneHTML = "/Scorer/HTML/SavePhoneHTML";
        public static final String ClearUserSeasonCheck = "/Scorer/HTML/ClearUserSeasonCheck";
        public static final String UpdateUserInfo = "/Scorer/HTML/UpdateUserInfo";
        public static final String GetSeasonListRec = "/Scorer/HTML/GetSeasonListRec";
    }

    public static class ScorerHTMLShareData{
        public static final String GetWXSignature = "/Scorer/HTML/ShareData/GetWXSignature";
        public static final String GetSeasonResultFull = "/Scorer/HTML/ShareData/GetSeasonResultFull";      //需更新
        public static final String GetZoneResultFull = "/Scorer/HTML/ShareData/GetZoneResultFull";
        public static final String GetSeasonCard = "/Scorer/HTML/ShareData/GetSeasonCard";
        public static final String GetUserPoster = "/Scorer/HTML/ShareData/GetUserPoster";
        public static final String GetSeasonLiveList = "/Scorer/HTML/ShareData/GetSeasonLiveList";
        public static final String GetSeasonResult = "/Scorer/HTML/ShareData/GetSeasonResult";
        public static final String GetZoneInfoByZoneId = "/Scorer/HTML/ShareData/GetZoneInfoByZoneId";
        public static final String GetSeasonInZone = "/Scorer/HTML/ShareData/GetSeasonInZone";
        public static final String GetZoneScorerByZoneId = "/Scorer/HTML/ShareData/GetZoneScorerByZoneId";
        public static final String ShowRecorderIMG = "/Scorer/HTML/ShareData/ShowRecorderIMG";              //需更新
    }

    public static class ScorerHTMLZone{
        public static final String GetZoneInfo = "/Scorer/HTML/Zone/GetZoneInfo";
        public static final String GetZoneTeamList = "/Scorer/HTML/Zone/GetZoneTeamList";
        public static final String GetTeamSignUp = "/Scorer/HTML/Zone/GetTeamSignUp";
        public static final String GetTeamInfo = "/Scorer/HTML/Zone/GetTeamInfo";
        public static final String CheckTeamInfoByUid = "/Scorer/HTML/Zone/CheckTeamInfoByUid";
        public static final String AddPlayerToZoneTeam = "/Scorer/HTML/Zone/AddPlayerToZoneTeam";
        public static final String GetTeamPlayersInZone = "/Scorer/HTML/Zone/GetTeamPlayersInZone";
        public static final String GetInvitationManageInfo = "/Scorer/HTML/Zone/GetInvitationManageInfo";
        public static final String GetInvitationManage = "/Scorer/HTML/Zone/GetInvitationManage";
    }

}

