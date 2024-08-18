package com.dpro.apdwfa.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import com.dpro.apdwfa.model.LoginUser;
import com.dpro.apdwfa.utils.SessionUtils;

public class CustomAuthenticationSuccessHandler  extends SimpleUrlAuthenticationSuccessHandler {

	private static final Logger logger = LogManager.getLogger(CustomAuthenticationSuccessHandler.class);
    private String defaultTargetUrl;

    public CustomAuthenticationSuccessHandler(String defaultTargetUrl) {
        this.defaultTargetUrl = defaultTargetUrl;
    }

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // HttpSession session = request.getSession();
        
        // 取得使用者訊息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userDetails.getUsername());
        
        // 將使用者訊息存入 session
        SessionUtils.setLoginUser(loginUser);

        logger.info("User [ " + loginUser.getUserId() + " ] logged in successfully.");

        // 跳轉頁面
        super.setDefaultTargetUrl(defaultTargetUrl);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
