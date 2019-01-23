package com.huanleichen.my.shop.web.admin.web.controller;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.domain.TbUser;
import com.huanleichen.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TbUserInfoController {
    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser = null;

        if (id == null) {
            tbUser = new TbUser();
        }

        else {
            tbUser = tbUserService.getTbUserById(id);
        }

        return tbUser;
    }

    @RequestMapping(value = {"tbUserInfo"}, method = RequestMethod.GET)
    public String query(Model model) {
        //查询所有的 tbUser 的信息
        List<TbUser> tbUsers = tbUserService.selectAll();
        //将查询得到的 List 集合放在 Model 中
        model.addAttribute("tbUserInfo", tbUsers);
        //将视图名称返回给 DispatcherServlet
        return "tbUserInfo";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model) {
        return "userform";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model) {
        BaseResult result = tbUserService.save(tbUser);

        model.addAttribute("result", result);

        if (result.getStatus() == BaseResult.SUCCESS_STATUS) {
            return query(model);
        } else {
            return form(model);
        }


    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(TbUser tbUser, Model model) {
        System.out.println(tbUser);
        model.addAttribute("tbUserInfo", tbUserService.search(tbUser));

        return "tbUserInfo";
    }
}
