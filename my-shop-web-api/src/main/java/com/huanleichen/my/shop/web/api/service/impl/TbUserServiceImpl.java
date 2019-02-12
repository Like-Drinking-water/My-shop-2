package com.huanleichen.my.shop.web.api.service.impl;

import com.huanleichen.my.shop.domain.TbUser;
import com.huanleichen.my.shop.web.api.dao.TbUserDao;
import com.huanleichen.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao dao;

    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = dao.login(tbUser.getEmail());

        if (user != null) {
            //对明文密码进行加密
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            //在与加密的密码进行比较
            if (password.equals(user.getPassword())) {
                return user;
            }
        }

        return null;
    }
}
