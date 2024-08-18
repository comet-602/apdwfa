package com.dpro.apdwfa.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.dpro.apdwfa.utils.SessionUtils;
import com.dpro.apdwfa.model.LoginUser;

@Controller
public class WebController {

    private static final Logger logger = LogManager.getLogger(SessionUtils.class);

    @GetMapping("/home")
    public String home(Model model) {
    
        //驗證userId，並取出值
        LoginUser loginUser = SessionUtils.getLoginUser();
        String userId ="";
        if (loginUser != null) {
            userId = loginUser.getUserId();	
        }
        logger.info(userId);
        model.addAttribute("userId", userId);
        return "home";
    }
}