package com.huanleichen.my.shop.web.ui.controller;

import com.google.code.kaptcha.Constants;
import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.utils.EmailUtils;
import com.huanleichen.my.shop.web.ui.api.UserAPI;
import com.huanleichen.my.shop.web.ui.dto.TbUserDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private EmailUtils emailUtils;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(TbUserDTO tbUserDTO, Model model, HttpServletRequest request) throws EmailException {
        if (!checkVerification(tbUserDTO, request)) {
            model.addAttribute("baseResult", BaseResult.failResult("验证码错误！"));
            return "login";
        }
        TbUserDTO user = null;
        try {
            user = UserAPI.login(tbUserDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //登录失败
        if (user == null) {
            model.addAttribute("baseResult", BaseResult.failResult("邮箱或密码错误，请重新输入！"));
            return "login";
        }
        //登录成功
        else {
            emailUtils.send("My Shop 登录提醒", String.format("亲爱的[%s]:\n\t您好！\n\t您的账号登录了 My Shop", user.getUsername()), user.getEmail());
            request.getSession().setAttribute("user", user);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index";
    }

    /**
     * 验证验证码是否正确
     * @param tbUserDTO
     * @param request
     * @return
     */
    private Boolean checkVerification(TbUserDTO tbUserDTO, HttpServletRequest request) {
        String verification = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equalsAnyIgnoreCase(verification, tbUserDTO.getVerification())) {
            return true;
        }
        return false;
    }
}
