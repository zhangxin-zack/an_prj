package com.scorer.client.values;

import java.util.HashMap;

public class ResultMap {

    public static final HashMap<Integer,String> Result= new HashMap<>();
    static{
        Result.put(9999,"token校验失败");
        Result.put(9988,"胜道接口加密校验失败");
        Result.put(9977,"胜道接口加密校验有重复");
        Result.put(8888,"用户登录失效");
        Result.put(7777,"微信登录失败");
        Result.put(5555,"微信账号认证错误");
        Result.put(3333,"有不允许的重复值");
        Result.put(1,"OK");
        Result.put(1001,"请先绑定手机号码");
        Result.put(1002,"发送验证码成功");
        Result.put(1003,"验证码错误");
        Result.put(1004,"该手机号已被其他人注册是否重新绑定");
        Result.put(1005,"比赛类型错误(为0,或者为空)");
        Result.put(1006,"微信登录失败!");
        Result.put(1007,"该手机号未注册");                     //该裁判不是注册裁判
        Result.put(1008,"两个队伍名字一样或id一样");
        Result.put(1009,"该手机号已注册,但是还未绑定微信,是否绑定微信");
        Result.put(1010,"参赛人员不够");
        Result.put(1011,"球衣号不可重复");
        Result.put(1012,"比赛中不可有相同球员");//uid
        Result.put(1013,"该手机号未绑定微信");
        Result.put(1014,"该手机号未注册记分宝");
        Result.put(1015,"老用户尝试走注册接口");
        Result.put(1016,"联赛中已经有该球队");
        Result.put(1017,"您已经在联赛中(创建球队或)加入球队");
        Result.put(1018,"该比赛正在进行中");
        Result.put(1020,"该比赛已被删除");
        Result.put(2001,"用户名或密码错误");
        Result.put(2002,"微信登录码过期");
        Result.put(2003,"手机验证码过期");
        Result.put(3000,"胜道加人接口传入zone_id没有不是胜道联赛");
        Result.put(3001,"胜道加人接口传入数据有问题");
        Result.put(3002,"胜道修改用户手机号接口传入数据有问题");
        Result.put(3003,"没有该用户（旧手机号，身份证，胜道YYID全对应）");
        Result.put(3004,"该组信息已过期");
        Result.put(3005,"所选球队与原来相同");

        Result.put(3031,"该分组内有比赛，不能删除");
        Result.put(3032,"该球队在分组内已有比赛，不能删除");
        Result.put(3033,"有球队在分组内已有比赛，不能删除");
        Result.put(3034,"分组信息不完整");
        Result.put(3035,"分组名字不能重复");
        Result.put(3036,"仍有分组不可删除");
        Result.put(3037,"阶段信息不完整");                     //大循环
        Result.put(3038,"请填写完整信息");                     //创建比赛用

        Result.put(5001,"未填写手机号或者密码");
        Result.put(5002,"没有该用户");
        Result.put(5006,"已有该用户");
        Result.put(5003,"用户密码错");
        Result.put(5004,"请求超时");
        Result.put(5005,"新老密码不能相同");

        Result.put(3011,"该用户不是胜道用户");
        Result.put(3012,"该用户没有签到");
        Result.put(3021,"比赛即将开始，无法更换比赛场地");
        Result.put(3022,"积分不足");
        Result.put(3010,"有不允许的空值");

        Result.put(4001,"连接密码错误");
        Result.put(4002,"分享链接已失效");
        Result.put(4003,"您已经是该联赛的记录员");
        Result.put(4004,"您已经是该联赛的管理员");
        Result.put(4005,"您已分享成功");

        Result.put(404,"Page No Found");
        Result.put(401,"Error Auth");
        Result.put(500,"Server Error");

        Result.put(21,"No Code Need ReturnUrl");
        Result.put(22,"No Code Need ReturnUrl");

    }
}
