package com.huanleichen.my.shop.web.admin.web.controller;

import com.huanleichen.my.shop.commons.contants.ContantUtils;
import com.huanleichen.my.shop.commons.utils.CookieUtils;
import com.huanleichen.my.shop.domain.User;
import com.huanleichen.my.shop.web.admin.service.LoginService;
import com.huanleichen.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = {"", "login"}, method = GET)
    public String login(HttpServletRequest request) {
        String password = null;
        String email = null;

        Cookie[] cookies = request.getCookies();

        String userInfo = CookieUtils.getCookieValue(request, ContantUtils.USER_INFO);

        if (userInfo != null && !userInfo.isEmpty()) {
            String[] info = userInfo.split(":");

            email = info[0];
            password = info[1];

            //将获取到的邮箱及密码放在request域中
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("remember", "1");
        }

        return "login";
    }

    @RequestMapping(value = "login", method = POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password,
                        String remember, HttpServletRequest request, HttpServletResponse response) {
        boolean rem = false;

        if (remember != null) {
            rem = true;
        }

        User user = loginService.login(email, password, rem);

        //登录成功
        if (user != null) {
            request.getSession().setAttribute(ContantUtils.SESSION_NAME, user);
            //如果用户选择记住密码
            if (user.getRemember()) {
               CookieUtils.setCookie(request, response, ContantUtils.USER_INFO,
                       String.format("%s:%s", email, password), 604800);
            }
            //如果用户没有选择记住密码
            else {
                CookieUtils.deleteCookie(request, response, ContantUtils.USER_INFO);
            }
            return "main";
        }

        //登录失败
        else {
            request.setAttribute("message", "邮箱或密码错误");
            return login(request);
        }

    }

    @RequestMapping(value = "logout", method = GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();

        return login(request);
    }
}
