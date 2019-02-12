package com.huanleichen.my.shop.web.admin.web.controller;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.domain.TbUser;
import com.huanleichen.my.shop.web.admin.service.TbUserService;
import com.huanleichen.my.shop.web.admin.service.abstracts.AbstractBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class TbUserInfoController extends AbstractBaseController<TbUser, TbUserService> {

    @ModelAttribute
    public TbUser getTbUser(Long id) {
        return super.getEntity(id, TbUser.class);
    }

    @Override
    @RequestMapping(value = {"tbUserInfo"}, method = RequestMethod.GET)
    public String list() {

        //将视图名称返回给 DispatcherServlet
        return "tbUserInfo";
    }

    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "userform";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes) {
        return super.save(tbUser, model, redirectAttributes, "tbUserInfo", "userform");
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "deleteMulti", method = RequestMethod.POST)
    public BaseResult deleteMulti(String ids) {
        return super.deleteMulti(ids);
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public PageInfo<TbUser> page(HttpServletRequest request, TbUser tbUser) {

        return super.page(request, tbUser);

    }

    @ResponseBody
    @RequestMapping(value = "isEmailExist", method = RequestMethod.POST)
    public Map<String, Boolean> isEmailExist(String email) {
        Map<String, Boolean> data = new HashMap<String, Boolean>();

        boolean isExist = false;

        if (service.isEmailExist(email, new TbUser())) {
            isExist = true;
        }

        data.put("isExist", isExist);
        return data;
    }

}
