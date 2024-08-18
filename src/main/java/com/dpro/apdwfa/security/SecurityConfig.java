package com.dpro.apdwfa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers("/login", "/logout").permitAll()
                        .requestMatchers("/home").authenticated()
                        .anyRequest().denyAll())
                .formLogin((form) -> form
                        // .loginPage("/login") // 設定自訂的登入頁面
                        // .defaultSuccessUrl("/home", true) // 登陸後跳轉畫面
                        .successHandler(authenticationSuccessHandler()) //處理登陸後邏輯
                        .permitAll()) // 用初始登入頁面
                .logout((logout) -> logout.permitAll()); // 允許所有用戶訪問登出頁面

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin1"))
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user1"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
    
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
            return new CustomAuthenticationSuccessHandler("/home");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}