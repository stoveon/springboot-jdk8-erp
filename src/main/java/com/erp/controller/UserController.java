package com.erp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    /**
     * <pre>
     *     사용자 목록 조회
     * </pre>
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user",method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/main");
        log.info("{} | {}", request.getMethod(), request.getRequestURI());
        return mav;
    }

    /**
     * <pre>
     *     사용자 목록 조회
     * </pre>
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user/list",method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/list");
        log.info("{} | {}", request.getMethod(), request.getRequestURI());
        return mav;
    }
}
