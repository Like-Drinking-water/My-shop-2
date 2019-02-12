package com.huanleichen.my.shop.web.api.web.controller.v1;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.domain.TbUser;
import com.huanleichen.my.shop.web.api.service.TbUserService;
import com.huanleichen.my.shop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/users")
public class TbUserController {
    @Autowired
    private TbUserService service;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser) {
        TbUser user = service.login(tbUser);

        //登录失败
        if (user == null) {
            return BaseResult.failResult("账号或密码错误");
        }

        else {
            TbUserDTO dto = new TbUserDTO();
            BeanUtils.copyProperties(user, dto);
            return BaseResult.successResult("登录成功", dto);
        }
    }
}
