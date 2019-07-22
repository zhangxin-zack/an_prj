package com.scorer.client.tools;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageApi {

    private static String URI_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";
    private static String URI_BATCH = "https://sms.yunpian.com/v2/sms/batch_send.json";
    private static String ENCODING = "UTF-8";

    private static String APIKEY = "3008c5dea5808dbd324d16f17aad2a3a";

    public static String sendSMS(String text, String mobile) {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", APIKEY);
        params.put("text", text);
        params.put("mobile", mobile);
        return OK_POST(URI_SEND_SMS, params);
    }

    public static String batchSend(String text, String mobile) {
        Map<String, String> params = new HashMap<>();//请求参数集合
        params.put("apikey", APIKEY);
        params.put("text", text);
        params.put("mobile", mobile);
        return OK_POST(URI_BATCH, params);//请自行使用post方式请求,可使用Apache HttpClient
    }

    private static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, ENCODING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

    private static String OK_POST(String url, Map<String, String> paramsMap) {
        return new HTTP_Tools().OK_PostParam_Sync(url, paramsMap);
    }
}
