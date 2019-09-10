package com.scorer.client.tools;

//如果JDK版本低于1.8,请使用三方库提供Base64类
//import org.apache.commons.codec.binary.Base64;
import com.google.gson.Gson;
import com.scorer.client.values.HuaWeiMSGResponse;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpHeaders;
import java.text.SimpleDateFormat;
import java.util.*;
//如果JDK版本是1.8,可使用原生Base64类


public class HuaWeiMessageApi {

    //无需修改,用于格式化鉴权头域,给"X-WSSE"参数赋值
    private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";
    //无需修改,用于格式化鉴权头域,给"Authorization"参数赋值
    private static final String AUTH_HEADER_VALUE = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";

    public static HuaWeiMSGResponse sendMSG(String phone, Integer code){

        //必填,请参考"开发准备"获取如下数据,替换为实际值
        String url = "https://rtcsms.cn-north-1.myhuaweicloud.com:10743/sms/batchSendSms/v1"; //APP接入地址+接口访问URI
        String appKey = "5Z4Ro70T1s3f8yRFq853luXKzquo"; //APP_Key
        String appSecret = "HIh6vge25S2Q4d7W31t07wcSuCVn"; //APP_Secret
        String sender = "8819091022337"; //国内短信签名通道号或国际/港澳台短信通道号
        String templateId = "681bac9f47174b0aae2e4df043f9e7a3"; //模板ID

        //条件必填,国内短信关注,当templateId指定的模板类型为通用模板时生效且必填,必须是已审核通过的,与模板类型一致的签名名称
        //国际/港澳台短信不用关注该参数
        String signature = "景瀚信息"; //签名名称

        //必填,全局号码格式(包含国家码),示例:+8615123456789,多个号码之间用英文逗号分隔
        String receiver = "+86"+phone; //短信接收人号码

        //选填,短信状态报告接收地址,推荐使用域名,为空或者不填表示不接收状态报告
        String statusCallBack = "";

        /**
         * 选填,使用无变量模板时请赋空值 String templateParas = "";
         * 单变量模板示例:模板内容为"您的验证码是${NUM_6}"时,templateParas可填写为"[\"369751\"]"
         * 双变量模板示例:模板内容为"您有${NUM_2}件快递请到${TXT_20}领取"时,templateParas可填写为"[\"3\",\"人民公园正门\"]"
         * ${DATE}${TIME}变量不允许取值为空,${TXT_20}变量可以使用英文空格或点号替代空值,${NUM_6}变量可以使用0替代空值
         * 查看更多模板和变量规范:产品介绍>模板和变量规范
         */
        String templateParas = "[\""+code+"\"]"; //模板变量

        //请求Headers中的X-WSSE参数值
        String wsseHeader = buildWsseHeader(appKey, appSecret);
        Map<String,String> param = new HashMap<>();
        Map<String,String> header = new HashMap<>();
        param.put("from", sender);
        param.put("to", receiver);
        param.put("templateId", templateId);
        param.put("signature", signature);
        if(null != templateParas && !templateParas.isEmpty()){
            param.put("templateParas", templateParas);
        }
        header.put(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded");
        header.put(HttpHeaders.AUTHORIZATION,AUTH_HEADER_VALUE);
        header.put("X-WSSE",wsseHeader);
        String response = new HTTP_Tools().OK_PostParam_Sync(url, param,header);
        return new Gson().fromJson(response,HuaWeiMSGResponse.class);
    }


    /**
     * 构造X-WSSE参数值
     * @param appKey
     * @param appSecret
     * @return
     */
    private static String buildWsseHeader(String appKey, String appSecret) {
        if (null == appKey || null == appSecret || appKey.isEmpty() || appSecret.isEmpty()) {
            System.out.println("buildWsseHeader(): appKey or appSecret is null.");
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date()); //Created
        String nonce = UUID.randomUUID().toString().replace("-", ""); //Nonce

        byte[] passwordDigest = DigestUtils.sha256(nonce + time + appSecret);
        String hexDigest = Hex.encodeHexString(passwordDigest);

        //如果JDK版本是1.8,请加载原生Base64类,并使用如下代码
        String passwordDigestBase64Str = Base64.getEncoder().encodeToString(hexDigest.getBytes()); //PasswordDigest

        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
    }

}
