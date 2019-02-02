package com.huanleichen.my.shop.web.admin.dao;

import com.huanleichen.my.shop.commons.persistence.BaseDao;
import com.huanleichen.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    /**
     * 通过 email 获取对应的 tbUser
     * @param email 获取的 tbUser 的邮箱
     * @return 放回对应的 tbUser
     */
    public TbUser selectByEmail(String email);
}
