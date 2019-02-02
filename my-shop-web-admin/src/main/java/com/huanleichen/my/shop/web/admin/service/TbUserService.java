package com.huanleichen.my.shop.web.admin.service;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.commons.persistence.BaseService;
import com.huanleichen.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService extends BaseService<TbUser> {
    /**
     * 通过邮箱和密码来验证是否可以登录
     *
     * @param email    登录的邮箱
     * @param password 登录的密码
     * @param remember 是否要记住密码
     * @return 登录成功返回对应的 tbUser
     * 失败返回 null
     */
    public TbUser login(String email, String password, boolean remember);

    /**
     * 查看邮箱是否已存在
     * @param email
     * @return
     */
    public boolean isEmailExist(String email, TbUser tbUser);

}