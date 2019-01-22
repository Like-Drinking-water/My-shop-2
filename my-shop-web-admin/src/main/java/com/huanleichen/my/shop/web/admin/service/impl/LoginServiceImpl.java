package com.huanleichen.my.shop.web.admin.service.impl;

import com.huanleichen.my.shop.domain.User;
import com.huanleichen.my.shop.web.admin.dao.UserDao;
import com.huanleichen.my.shop.web.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Qualifier("userDaoImpl")
    @Autowired
    private UserDao dao;

    public User login(String email, String password, boolean remember) {

        User user = dao.getUserByEmailAndPassword(email, password, remember);
        return user;
    }

}
