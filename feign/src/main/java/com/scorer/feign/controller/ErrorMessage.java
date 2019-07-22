package com.scorer.feign.controller;

import com.scorer.feign.values.ResultMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ErrorMessage {

    @RequestMapping(value = "/404.html")
    @ResponseBody
    public Map ErrorMessage404() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", 404);
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }

    @RequestMapping(value = "/401.html")
    @ResponseBody
    public Map ErrorMessage401() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", 401);
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }

    @RequestMapping(value = "/500.html")
    @ResponseBody
    public Map ErrorMessage500() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", 500);
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }
}
