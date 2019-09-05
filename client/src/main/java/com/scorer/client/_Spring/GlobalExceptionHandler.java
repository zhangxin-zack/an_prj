/*
package com.scorer.client._Spring;

import com.scorer.client._Excptions.Secret_SD_Used_Exception;
import com.scorer.client.values.ResultMap;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {   //全局异常捕获

    @ExceptionHandler(value = com.scorer.client._Excptions.UserH5CookieTimeOutException.class)
    public String jsonErrorHandler(HttpServletRequest request, com.scorer.client._Excptions.UserH5CookieTimeOutException ex) throws Exception {
        request.setAttribute("result", "安全认证失效,请重新打开链接");
        System.err.println(ex.getMessage());
        return "user/error";
    }

    @ExceptionHandler(value = com.scorer.client._Excptions.UserH5CookieTimeOutException_Json.class)
    @ResponseBody
    public Map jsonErrorHandler(HttpServletRequest request, com.scorer.client._Excptions.UserH5CookieTimeOutException_Json ex) throws Exception {
        System.err.println(ex.getMessage());
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", 8888);
        //用户登录失效
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }

    @ExceptionHandler(value = com.scorer.client._Excptions.AdminCookieTimeOutException.class)
    @ResponseBody
    public Map jsonErrorHandler(HttpServletRequest request, com.scorer.client._Excptions.AdminCookieTimeOutException ex) throws Exception {
        System.err.println(ex.getMessage());
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", 9999);
        //管理员登录失效
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }

    @ExceptionHandler(value = com.scorer.client._Excptions.TokenTimeOutException.class)
    @ResponseBody
    public Map jsonErrorHandler(HttpServletRequest request, com.scorer.client._Excptions.TokenTimeOutException ex) throws Exception {
        System.err.println(ex.getMessage());
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", 9999);
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }

    @ExceptionHandler(value = com.scorer.client._Excptions.WX_ERROR_Exception.class)
    @ResponseBody
    public Map jsonErrorHandler(HttpServletRequest request, com.scorer.client._Excptions.WX_ERROR_Exception ex) throws Exception {
        System.err.println(ex.getMessage());
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", 5555);
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }

    //数据库重复
    @ExceptionHandler(value = MySQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public Map jsonErrorHandlerSQL(MySQLIntegrityConstraintViolationException ex) {
        System.out.println(ex.getMessage());
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", 3333);
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }

    @ExceptionHandler(value = com.scorer.client._Excptions.Secret_SD_Exception.class)
    @ResponseBody
    public Map jsonErrorHandlerSQL(com.scorer.client._Excptions.Secret_SD_Exception ex) {
        System.out.println(ex.getMessage());
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", 9988);
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }

    @ExceptionHandler(value = com.scorer.client._Excptions.Secret_SD_Used_Exception.class)
    @ResponseBody
    public Map jsonErrorHandlerSQL(Secret_SD_Used_Exception ex) {
        System.out.println(ex.getMessage());
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", 9977);
        responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
        return responseMap;
    }
}*/
