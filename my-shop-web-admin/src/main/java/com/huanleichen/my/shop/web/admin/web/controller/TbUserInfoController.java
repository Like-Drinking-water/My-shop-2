package com.huanleichen.my.shop.web.admin.web.controller;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.domain.TbUser;
import com.huanleichen.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String userInfo() {

        //将视图名称返回给 DispatcherServlet
        return "tbUserInfo";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "userform";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes) {
        BaseResult result = tbUserService.save(tbUser);

        model.addAttribute("result", result);

        if (result.getStatus() == BaseResult.SUCCESS_STATUS) {
            redirectAttributes.addFlashAttribute("result", result);
            return "redirect:tbUserInfo";
        } else {
            model.addAttribute("result", result);
            return "userform";
        }


    }

    @ResponseBody
    @RequestMapping(value = "deleteMulti", method = RequestMethod.POST)
    public BaseResult deleteMulti(String ids) {
        BaseResult baseResult = null;

        if (!StringUtils.isBlank(ids)) {
            String[] deleteArray = ids.split(",");
            tbUserService.deleteMulti(deleteArray);
            baseResult = BaseResult.successResult("删除成功");
        }

        else {
            baseResult = BaseResult.failResult("删除失败");
        }

        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request, TbUser tbUser) {

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw != null? Integer.valueOf(strDraw) : 0;
        int start = strStart != null? Integer.valueOf(strStart) : 0;
        int length = strLength != null? Integer.valueOf(strLength) : 10;

        return tbUserService.getPage(start, length, draw, tbUser);

    }

    @ResponseBody
    @RequestMapping(value = "isEmailExist", method = RequestMethod.POST)
    public Map<String, Boolean> isEmailExist(String email) {
        Map<String, Boolean> data = new HashMap<String, Boolean>();

        boolean isExist = false;

        if (tbUserService.isEmailExist(email, new TbUser())) {
            isExist = true;
        }

        data.put("isExist", isExist);
        return data;
    }

}
