package com.scorer.client.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class OpCookie {
	public static String getCookieByName(HttpServletRequest request,String cookieName){
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
            for(Cookie cookie:cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
		return null;
	}
	
}
