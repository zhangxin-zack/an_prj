package com.scorer.feign.values;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public final class StatusList {

    public static final HashMap<String,Integer> Status = new HashMap<>();
    public static final HashMap<Integer,String> StatusName = new HashMap<>();

    public static HashMap<Integer,Integer> PointStatusId =new HashMap<>();
    public static Set<Integer> FoulStatusId =new HashSet<>();

    static {
        FoulStatusId.add(11);
        FoulStatusId.add(12);
        FoulStatusId.add(13);
        FoulStatusId.add(14);
        FoulStatusId.add(15);
    }

    static {
        PointStatusId.put(1,1);
        PointStatusId.put(2,2);
        PointStatusId.put(3,3);
        PointStatusId.put(7,4);
    }

    static{
        Status.put("OnePoint",1);                   //1分
        Status.put("TwoPoint",2);                   //2分
        Status.put("ThreePoint",3);                 //3分
        Status.put("MissOnePoint",4);               //1分miss
        Status.put("MissTowPoint",5);               //2分miss
        Status.put("MissThreePoint",6);             //3分miss
        Status.put("FourPoint",7);                  //4分
        Status.put("MissFourPoint",8);              //4分miss

        Status.put("PersonalFoul",11);              //个人犯规
        Status.put("UnsportsmanlikeConduct",12);    //违体犯规
        Status.put("TechnicalFoul",13);             //技术犯规
        Status.put("Disqualification",14);          //夺权犯规
        Status.put("TeamFoul",15);                  //球队犯规,教练员犯规


        Status.put("OffensiveRebound",21);          //进攻篮板,前场篮板
        Status.put("DefensiveRebound",22);          //防守篮板,后场篮板
        Status.put("Cap",23);                       //盖帽
        Status.put("Steal",24);                     //抢断
        Status.put("Assist",25);                    //助攻
        Status.put("Fault",26);                     //失误

        Status.put("Rebound",27);                   //篮板


        Status.put("FirstLaunch",31);
        Status.put("Enter",32);
        Status.put("Exit",33);
        Status.put("Exchange",34);                  //换人

        Status.put("StartGame",41);                 //开始比赛
        Status.put("StartPart",42);                 //小节开始
        Status.put("ResumeGame",43);                //恢复比赛
        Status.put("PauseGame",44);                 //暂停比赛(停表)
        Status.put("TeamPauseGame",45);             //球队叫暂停
        Status.put("EndPart",46);                   //小节结束
        Status.put("EndGame",47);                   //比赛结束

        Status.put("AddGame",51);                   //首次进入比赛需要处理的数据(在SeasonReady后)
    }

    static{
        StatusName.put(1,"1分命中");
        StatusName.put(2,"2分命中");
        StatusName.put(3,"3分命中");
        StatusName.put(4,"1分未进");
        StatusName.put(5,"2分未进");
        StatusName.put(6,"3分未进");
        StatusName.put(7,"4分命中");
        StatusName.put(8,"4分未进");

        StatusName.put(11,"个人犯规");
        StatusName.put(12,"违体犯规");
        StatusName.put(13,"技术犯规");
        StatusName.put(14,"取消比赛资格");
        StatusName.put(15,"教练员犯规");

        StatusName.put(21,"前场篮板");
        StatusName.put(22,"后场篮板");
        StatusName.put(23,"盖帽");
        StatusName.put(24,"抢断");
        StatusName.put(25,"助攻");
        StatusName.put(26,"失误");
        StatusName.put(27,"篮板");

        StatusName.put(31,"首发");
        StatusName.put(32,"入场");
        StatusName.put(33,"下场");
        StatusName.put(34,"换人");

        StatusName.put(41,"开始比赛");
        StatusName.put(42,"小节开始");
        StatusName.put(43,"恢复比赛");
        StatusName.put(44,"暂停比赛");
        StatusName.put(45,"请求暂停");
        StatusName.put(46,"小节结束");
        StatusName.put(47,"比赛结束");
    }
}
