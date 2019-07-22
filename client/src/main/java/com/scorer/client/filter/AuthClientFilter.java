package com.scorer.client.filter;

import com.scorer.client.tools.RedisInFilter;
import com.scorer.client.tools.TestObject;
import com.scorer.tools.EncryptTool;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthClientFilter implements Filter {

    public AuthClientFilter() {
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
        String str = request.getHeader("Feign_str_next");
        String enStr = request.getHeader("Feign_enStr_next");
        if (TestObject.noneEmpty(str, enStr) && RedisInFilter.CheckUpStr(str)) {
            String deStr = EncryptTool.decodeScorerSC(enStr);
            if (Objects.equals(str, deStr)) {
                System.out.println("AuthClientFilter Check Success!");
                chain.doFilter(request, response);
            } else {
                System.out.println("AuthClientFilter Check Fail!");
            }
        }
    }

}
