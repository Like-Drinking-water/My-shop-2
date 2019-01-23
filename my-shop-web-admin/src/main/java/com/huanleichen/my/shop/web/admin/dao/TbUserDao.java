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
    public void delete(Long id);

    /**
     * 获取一个 tbUser
     * @param id 要获取的 tbUser 的 ID
     */
    public TbUser getTbUserById(Long id);

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

    /**
     * 通过 email 获取对应的 tbUser
     * @param email 获取的 tbUser 的邮箱
     * @return 放回对应的 tbUser
     */
    public TbUser selectByEmail(String email);

    /**
     * 根据传入的 tbUser 信息查询所有相匹配的 tbUser
     * @param tbUser
     * @return
     */
    public List<TbUser> search(TbUser tbUser);
}
