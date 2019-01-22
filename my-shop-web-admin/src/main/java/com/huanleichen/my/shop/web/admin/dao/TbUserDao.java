package com.huanleichen.my.shop.web.admin.dao;

import com.huanleichen.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserDao {
    /**
     * 获取所有的 TbUser
     * @return 返回一个 TbUser 的 List 集合
     */
    public List<TbUser> selectAll();

    /**
     * 添加一个新的 tbUser
     * @param tbUser 要添加的 tbUser
     */
    public void insert(TbUser tbUser);

    /**
     * 删除一个 tbUser
     * @param id 要删除的 tbUser 的 ID
     */
    public void delete(long id);

    /**
     * 获取一个 tbUser
     * @param id 要获取的 tbUser 的 ID
     */
    public TbUser getTbUserById(long id);

    /**
     * 修改 tbUser 的信息
     * @param tbUser 保存修改信息的 tbUser
     */
    public void update(TbUser tbUser);

    /**
     * 通过用户名进行模糊查询
     * @param username 要查询的 tbUser 的用户名
     * @return 返回名字中含有 name 的 tbUser 的 List 集合
     */
    public List<TbUser> selectByName(String username);
}
