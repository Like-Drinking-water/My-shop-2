package com.huanleichen.my.shop.web.admin.dao;

import com.huanleichen.my.shop.domain.User;

public interface UserDao {
    public User getUserByEmailAndPassword(String email, String password, boolean remember);
}
