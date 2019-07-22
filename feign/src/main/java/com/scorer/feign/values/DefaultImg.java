package com.scorer.feign.values;

import java.util.Random;

public class DefaultImg {
    public static final String UserImg = "http://image.balledu.com/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F%E7%94%A8%E6%88%B7.jpg";     //个人头像-默认头像用户.jpg
    public static final String UserImg_Boy = "http://image.balledu.com/default_logo_boy.jpg";                                                                    //个人头像-男.jpg
    public static final String UserImg_Girl = "http://image.balledu.com/default_logo_girl.jpg";                                                                    //个人头像-女.jpg
    public static final String FieldLogo = "http://image.balledu.com/%E9%BB%98%E8%AE%A4%E5%9C%BA%E9%A6%86_v1.0.jpg";                //场地Logo-默认场馆_v1.0.jpg
    public static final String ZonePhoto = "http://image.balledu.com/backgroud_zone.jpg";                                           //联赛背景
    public static final String ZoneLogo = "http://image.balledu.com/zone_logo.jpg";                                                 //赛事logo(小图)
    public static final String ZoneSignUpBackground = "http://image.balledu.com/zone_sign_up_background.jpg";                       //赛事报名背景图

    public String TeamLogo = GetRandomTeamLogo();                                                                                   //球队头像(随机生成)
    public String TeamBackImg = "http://image.balledu.com/20170427080052b2a04f21-cc09-4e73-a714-310c7c8b9f5b.jpg";                  //球队背景
    private String GetRandomTeamLogo(){
        Random random = new Random();
        int num = random.nextInt(46)+1;
        String teamLogo = "http://image.balledu.com/teamLogo";
        return teamLogo+num+".jpg";
    }
}
