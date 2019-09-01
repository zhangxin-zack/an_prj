package com.scorer.client.tools;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.scorer.client.tools.TokenState;
import net.minidev.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;

/**
 * @author running@vip.163.com
 */

public class Jwt {


    /**
     * 秘钥
     */
    private static final byte[] SECRET = "3d990d22769174dfg43fgh1314c23df11fff26eed324542czd".getBytes();

    /**
     * 初始化head部分的数据为
     * {
     * "alg":"HS256",
     * "type":"JWT"
     * }
     */
    private static final JWSHeader header = new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);

    /**
     * 生成token，该方法只在用户登录成功后调用
     *
     * @param payload，可以存储用户id，token生成时间，token过期时间等自定义字段
     * @return token字符串, 若失败则返回null
     */
    public static String createToken(Map<String, Object> payload) {
        String tokenString = null;
        // 创建一个 JWS object
        JWSObject jwsObject = new JWSObject(header, new Payload(new JSONObject(payload)));
        try {
            // 将jwsObject 进行HMAC签名
            jwsObject.sign(new MACSigner(SECRET));
            tokenString = jwsObject.serialize();
        } catch (JOSEException e) {
            System.err.println("签名失败:" + e.getMessage());
            e.printStackTrace();
        }
        return tokenString;
    }

    /**
     * 校验token是否合法，返回Map集合,集合中主要包含    state状态码   data鉴权成功后从token中提取的数据
     * 该方法在过滤器中调用，每次请求API时都校验
     *
     * @param token
     * @return Map<String,Object>
     */
    public Map<String, Object> validToken(String token, String uid, RedisTemplate<String, String> redisTemplate) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier verifier = new MACVerifier(SECRET);
            String tokens = redisTemplate.opsForValue().get("uid" + uid);
            System.out.print("validToken" + "------------" + tokens);
            if (jwsObject.verify(verifier)) {
                JSONObject jsonOBj = payload.toJSONObject();
                // token校验成功（此时没有校验是否过期）
                resultMap.put("state", com.scorer.client.tools.TokenState.VALID.toString());
                // 若payload包含ext字段，则校验是否过期
                if (jsonOBj.containsKey("ext")) {
                    //String tokens=(String)request.getSession().getAttribute("token");
                    System.out.print("validToken" + "------------" + tokens);
                    // 过期了
                    if (tokens == null) {
                        resultMap.clear();
                        resultMap.put("state", com.scorer.client.tools.TokenState.EXPIRED.toString());
                    } else if (!tokens.equals(token)) {
                        resultMap.clear();
                        resultMap.put("state", com.scorer.client.tools.TokenState.EXPIRED.toString());
                    }
                    resultMap.put("data", jsonOBj);
                } else {
                    // 校验失败
                    resultMap.put("state", com.scorer.client.tools.TokenState.INVALID.toString());
                }
            } else {
                // 校验失败
                resultMap.put("state", com.scorer.client.tools.TokenState.INVALID.toString());
            }
            if (tokens == null) {
                resultMap.clear();
                resultMap.put("state", com.scorer.client.tools.TokenState.EXPIRED.toString());
            } else if (!tokens.equals(token)) {
                resultMap.clear();
                resultMap.put("state", com.scorer.client.tools.TokenState.EXPIRED.toString());
            } else {
                resultMap.put("state", com.scorer.client.tools.TokenState.VALID.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
            // token格式不合法导致的异常
            resultMap.clear();
            resultMap.put("state", TokenState.INVALID.toString());
        }
        return resultMap;
    }

}


