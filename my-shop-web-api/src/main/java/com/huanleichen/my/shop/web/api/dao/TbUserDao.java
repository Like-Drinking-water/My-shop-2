package com.huanleichen.my.shop.web.api.dao;

import com.huanleichen.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserDao {
    TbUser login(String email);
}
