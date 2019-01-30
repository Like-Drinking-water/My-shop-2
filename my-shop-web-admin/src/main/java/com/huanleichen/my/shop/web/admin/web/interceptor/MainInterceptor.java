package com.huanleichen.my.shop.web.admin.web.interceptor;

import com.huanleichen.my.shop.commons.contants.ContantUtils;
import com.huanleichen.my.shop.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TbUser user = (TbUser) request.getSession().getAttribute(ContantUtils.SESSION_NAME);

        //用户已登录
        if (user != null) {
            return true;
        }
        //用户未登录
        else {
            //重定向到登录页面
            response.sendRedirect("/myshop/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
