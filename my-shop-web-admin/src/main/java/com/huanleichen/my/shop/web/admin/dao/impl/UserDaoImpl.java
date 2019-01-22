package com.huanleichen.my.shop.web.admin.dao.impl;

import com.huanleichen.my.shop.domain.User;
import com.huanleichen.my.shop.web.admin.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public User getUserByEmailAndPassword(String email, String password, boolean remember) {
        User user = null;

        if (email.equals("admin@huanleichen.com")) {
            if (password.equals("admin")) {
                user = new User("admin@huanleichen.com", "admin", "admin", remember);
                logger.info("登录成功");
            }
        }

        return user;
    }
}
