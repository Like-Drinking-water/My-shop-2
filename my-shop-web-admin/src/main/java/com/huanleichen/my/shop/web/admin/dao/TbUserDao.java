package com.huanleichen.my.shop.web.admin.dao;

import com.huanleichen.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
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
     * 通过 email 获取对应的 tbUser
     * @param email 获取的 tbUser 的邮箱
     * @return 放回对应的 tbUser
     */
    public TbUser selectByEmail(String email);

    /**
     * 批量删除
     * @param ids
     */
    public void deleteMulti(String[] ids);

    /**
     * 获取分页
     * @param map start为开始 length为长度
     * @return
     */
    public List<TbUser> getPage(Map<String, Object> map);

    /**
     * 返回数据库的总记录数
     * @return
     */
    public int count(TbUser tbUser);
}
