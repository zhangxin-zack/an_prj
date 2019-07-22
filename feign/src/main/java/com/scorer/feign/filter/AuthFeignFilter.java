package com.scorer.feign.filter;

import com.scorer.feign.tools.RedisInFilter;
import com.scorer.feign.tools.TestObject;
import com.scorer.tools.EncryptTool;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthFeignFilter implements Filter {

    public AuthFeignFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest argo, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
        System.out.println("In AuthFeignFilter");
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpServletRequest request = (HttpServletRequest) argo;
        String str = request.getHeader("Gateway_str_next");
        String enStr = request.getHeader("Gateway_enStr_next");
        if (TestObject.noneEmpty(str, enStr) && RedisInFilter.CheckUpStr(str)) {
            String deStr = EncryptTool.decodeScorerSC(enStr);
            if (Objects.equals(str, deStr)) {
                System.out.println("AuthFeignFilter Check Success!");
                chain.doFilter(request, response);
            } else {
                System.out.println("AuthFeignFilter Check Fail!");
            }
        }
    }

}
