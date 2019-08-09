package com.scorer.client.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.scorer.client.tools.SysContent.getRequest;
import static com.scorer.client.tools.SysContent.getResponse;

@Component
@Aspect
@Order(7)  //定义切面执行的优先级，数字越低，优先级越高
public class Access_Control_Allow_Origin {

    @Value("${spring.allow-origin.secure}") //配置文件中的跨域请求设置
    private boolean acAccess;

    @Pointcut("execution(* com.scorer.client.controller..*(..))")
    public void Access_Control_Allow_Origin_AOP() {}

    @Before("Access_Control_Allow_Origin_AOP()")
    public void AddAccess() {
        System.err.println("Setting Access-Control-Allow-Origin");
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        System.err.println("开启跨域--->"+acAccess);
        if(acAccess){
            if(request.getHeader("Origin") != null){
                response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));      //测试用,开启跨域请求
            }
            response.setHeader("Access-Control-Allow-Credentials","true");                          //开启cookie
        }
    }

}