package com.erp.controller;

import com.erp.bean.UserInfo;
import com.erp.service.UserInfoService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserInfoService userInfoService;

    private Gson GSON = new Gson();

    /**
     * <pre>
     *     사용자 로그인 페이지
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @since 2024. 10. 02.
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login/loginForm");
        log.info("{} | {}", request.getMethod(), request.getRequestURI());
        return mav;
    }

    /**
     * <pre>
     *     사용자 로그인 페이지
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @since 2024. 10. 02.
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<>();
        String message = "fail";
        int status = -1;
        log.info("{} | {}", request.getMethod(), request.getRequestURI());

        String loginId = request.getParameter("userId");
        String password = request.getParameter("password");

        log.debug("loginId: {} | password: {}", loginId, password);

        try {

        } catch (Exception e) {
            log.error("{} | {}", e, e.getMessage());
        } finally {
            map.put("result", message);
            map.put("status", status);
        }

        return map;
    }


    /**
     * <pre>
     *     사용자 가입 페이지
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @since 2024. 10. 02.
     */
    @RequestMapping(value = "/login/regist", method = {RequestMethod.GET})
    public ModelAndView registForm(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login/registForm");
        log.info("{} | {}", request.getMethod(), request.getRequestURI());

        return mav;
    }

    /**
     * <pre>
     *     사용자 가입
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @since 2024. 10. 02.
     */
    @RequestMapping(value = "/login/regist", method = {RequestMethod.POST})
    public Map<String, Object> regist(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) {
        log.info("{} | {}", request.getMethod(), request.getRequestURI());
        log.debug("userInfo: {} ", GSON.toJson(userInfo));

        Map<String, Object> map = new HashMap<>();
        String message = "fail";
        int status = -1;
        String password = userInfo.getPassword();

        try {

            MessageDigest sha = java.security.MessageDigest.getInstance("SHA-256");

            byte[] shabytes;
            String tst = password;

            shabytes = sha.digest(tst.getBytes(StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < shabytes.length; i++) {
                sb.append(Integer.toString((shabytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            userInfo.setPassword(sb.toString());

            if (userInfo.getSid() == -1) {
                userInfoService.insertUserInfo(userInfo);
            } else {
                userInfoService.updateUserInfoBySid(userInfo);
            }

            message = "success";
            status = 0;

        } catch (Exception e) {
            log.error("{} | {}", e, e.getMessage());
        } finally {
            map.put("result", message);
            map.put("status", status);
        }


        return map;
    }


    /**
     * <pre>
     *     사용자 아이디 중복확인
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @since 2024. 10. 21.
     */
    @RequestMapping(value = "/login/check", method = {RequestMethod.POST})
    public Map<String, Object> loginIdCheck(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) {
        log.info("{} | {}", request.getMethod(), request.getRequestURI());
        log.debug("userInfo: {} ", GSON.toJson(userInfo));

        Map<String, Object> map = new HashMap<>();
        String message = "fail";
        int status = -1;

        try {
            UserInfo existUser = userInfoService.checkExistUserInfoToLoginId(userInfo);
            if (existUser == null) {
                message = "success";
                status = 0;
            } else {
                message = "exist";
                status = -2;
            }
        } catch (Exception e) {
            log.error("{} | {}", e, e.getMessage());
        } finally {
            map.put("result", message);
            map.put("status", status);
        }

        return map;
    }

}
