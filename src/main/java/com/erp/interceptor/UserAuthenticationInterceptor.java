package com.erp.interceptor;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 쿠키 로그인 이므로 이용 시간을 기록해야 함
//        if ( LoginUtil.isLogin(request) ) {
//            LoginUtil.setLogin(request, response, LoginUtil.getLoginId(request));
//            return true;
//        } else {
//            // 홈 화면일 때
//            if ( request.getServletPath().endsWith("/home/main") == true ) {
//                throw new LoginNotException();
//            }
//            // 그 외 화면일 때
//            else {
//                throw new LoginNotValidException();
//            }
//        }
        return true;
    }
}
