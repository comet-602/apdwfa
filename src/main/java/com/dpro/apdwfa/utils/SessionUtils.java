package com.dpro.apdwfa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dpro.apdwfa.model.LoginUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {

	private static final Logger logger = LogManager.getLogger(SessionUtils.class);

	private final static String SESSION_USER_DATA = "SESSION_USER_DATA";
	
	public static LoginUser getLoginUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();	
		HttpSession httpSession = request.getSession();

		LoginUser loginUser = (LoginUser)httpSession.getAttribute(SESSION_USER_DATA) ;
		
		//需要在功能頁顯示使用者名稱
		if( httpSession != null ){
			httpSession.setAttribute("userId", loginUser==null? "" : loginUser.getUserId());
		}
	
		logger.info("login information : " + ((loginUser != null) ? loginUser.getUserId() : "Null"));
		return loginUser;
	}

	public static void setLoginUser(LoginUser loginUser) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_USER_DATA, loginUser) ;
		
		logger.info("Set user information : " + ((loginUser != null) ? loginUser.getUserId() : "Null"));
	}
}
