package com.huanleichen.my.shop.web.admin.service;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {
    /**
     * 获取所有的 TbUser
     *
     * @return TbUser的 List 集合
     */
    public List<TbUser> selectAll();

    /**
     * 删除一个 tbUser
     *
     * @param id 要删除的 tbUser 的 ID
     */
    public void delete(Long id);

    /**
     * 获取一个 tbUser
     *
     * @param id 要获取的 tbUser 的 ID
     */
    public TbUser getTbUserById(Long id);

    /**
     * 修改 tbUser 的信息
     *
     * @param tbUser 保存修改信息的 tbUser
     */
    public void update(TbUser tbUser);

    /**
     * 通过用户名进行模糊查询
     *
     * @param username 要查询的 tbUser 的用户名
     * @return 返回名字中含有 name 的 tbUser 的 List 集合
     */
    public List<TbUser> selectByName(String username);

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
     * 保存提交的用户信息
     *
     * @param tbUser 提交的用户信息
     * @return 操作的结果
     */
    public BaseResult save(TbUser tbUser);

    /**
     * 根据传入的 tbUser 查找 tbUser
     *
     * @param tbUser
     * @return 返回相匹配的 tbUser 的 List 集合
     */
    public List<TbUser> search(TbUser tbUser);

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteMulti(String[] ids);

    /**
     * 获取分页
     * @param start 开始数据
     * @param length 获取长度
     * @return
     */
    public List<TbUser> getPage(int start, int length);

    /**
     * 返回数据库的总记录数
     * @return
     */
    public int count();

    /**
     * 查看邮箱是否已存在
     * @param email
     * @return
     */
    public boolean isEmailExist(String email);
}