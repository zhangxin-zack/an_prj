package com.scorer.feign.filter;

import com.scorer.feign.tools.ContentCachingRequestWrapperAn;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class Encoder implements Filter {

    public Encoder() {
    }

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest argo, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpServletRequest request = (HttpServletRequest) argo;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("---------   Request Start   ---------");
		System.out.println("requestURL---->" + request.getRequestURL());
		System.out.println("requestURI---->" + request.getRequestURI());
		System.out.println("------   Header Start   ------");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			System.out.println("InHeader:" + headerName + "------->" + request.getHeader(headerName));
		}
		System.out.println("------   Parameter Start   ------");
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			System.out.println("InParameter:" + parameterName + "------->" + request.getParameter(parameterName));
		}
		System.out.println("------   Attribute Start   ------");
		Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			if(!(attributeName.contains("apache.") || attributeName.contains("springframework.") || attributeName.contains("servlet."))){
				System.out.println("InAttribute:" + attributeName + "------->" + request.getAttribute(attributeName));
			}
		}
		ContentCachingRequestWrapperAn requestAn = null;
		try {
			requestAn = new ContentCachingRequestWrapperAn(request);
			String body = new String(requestAn.getBody(),requestAn.getCharacterEncoding());
			System.out.println("------   Request Body Start   ------");
			System.out.println(body);
			requestAn.setAttribute("SaveRequest_RequestBody",body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---------   Request End   ---------");
		HttpServletRequest requestNext = requestAn == null ? request : requestAn;
		chain.doFilter(requestNext, response);

	}

}
