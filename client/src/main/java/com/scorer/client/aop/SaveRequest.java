package com.scorer.client.aop;

import com.scorer.model.model1.Save_Request;
import com.scorer.client.service.Request_Service;
import com.scorer.client.tools.*;
import com.google.gson.Gson;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

import static com.scorer.client.tools.SysContent.getRequest;

@Component
@Aspect
@Order(1)  //定义切面执行的优先级，数字越低，优先级越高
public class SaveRequest {

    @Resource
    private Request_Service request_service;

    @Pointcut("execution(* com.scorer.client.controller..*(..))")
    public void AllController() {
    }

    @Before("AllController()")
    public void SaveAllRequest() {
        System.err.println("SaveAllRequest!");
        HttpServletRequest request = getRequest();
        Save_Request save_request = new Save_Request();
        try {
            Integer uid = Integer.valueOf(Objects.requireNonNull(
                    !ObjectUtils.isEmpty(request.getHeader("uid")) ? request.getHeader("uid") :
                            !ObjectUtils.isEmpty(OpCookie.getCookieByName(request, "cookie_uid_h5")) ? OpCookie.getCookieByName(request, "cookie_uid_h5") : request.getParameter("uid")));
            save_request.setUid(uid);
        } catch (NumberFormatException | NullPointerException ignored) {
        }
        try {
            save_request.setUser_agent(request.getHeader("user-agent"));
            save_request.setRequestURL(new String(request.getRequestURL()));
            save_request.setRequestIP(IpTools.getIpAddr(request));
            save_request.setRequestTime(System.currentTimeMillis());
            save_request.setRequestURI(request.getRequestURI());
            Map<String, String[]> parameterMap = request.getParameterMap();
            save_request.setParameterMap(EmojiFilter.FilterEmoji(new Gson().toJson(parameterMap)));
            String requestBody = (String) request.getAttribute("SaveRequest_RequestBody");
            if (!ObjectUtils.isEmpty(requestBody)) {
                save_request.setRequestBody(EmojiFilter.FilterEmoji(requestBody));
            }
            request_service.saveRequest(save_request);
        } catch (Throwable ignored) {
        }
    }
}
