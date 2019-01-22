package com.huanleichen.my.shop.web.admin.web.controller;

import com.huanleichen.my.shop.domain.TbUser;
import com.huanleichen.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TbUserInfoController {
    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = {"tbUserInfo"}, method = RequestMethod.GET)
    public String query(HttpServletRequest request) {
        //查询所有的 tbUser 的信息
        List<TbUser> tbUsers = tbUserService.selectAll();
        //将查询得到的 List 集合放在 request 域中
        request.setAttribute("tbUserInfo", tbUsers);
        //将视图名称返回给 DispatcherServlet
        return "tbUserInfo";
    }
}
