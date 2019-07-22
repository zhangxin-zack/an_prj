package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;

import java.util.HashMap;
import java.util.Map;

public class BaseSeviceImpl {

    public Map<String, Object> resultMap(int code, String msg, Object data){
        Map map = new HashMap<>();
        map.put(Iconstants.RESULT_CODE, code);
        map.put(Iconstants.RESULT_MESSAGE, msg);
        map.put(Iconstants.RESULT_DATA, data);
        return map;
    }

    public Map<String, Object> resultInfo(int code, String msg){
        Map map = new HashMap<>();
        map.put(Iconstants.RESULT_CODE, code);
        map.put(Iconstants.RESULT_MESSAGE, msg);
        return map;
    }
}
