package com.huanleichen.my.shop.web.admin.web.interceptor;

import com.huanleichen.my.shop.commons.contants.ContantUtils;
import com.huanleichen.my.shop.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        User user = (User) request.getSession().getAttribute(ContantUtils.SESSION_NAME);

        //如果用户已登录
        if (user != null) {
            //重定向到主页
            response.sendRedirect("main");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
