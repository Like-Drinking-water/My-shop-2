package com.huanleichen.my.shop.web.admin.dao;

import com.huanleichen.my.shop.domain.Content;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ContentDao {
    /**
     * 获取所有的 content
     * @return 返回一个 content 的 List 集合
     */
    public List<Content> selectAll();

    /**
     * 添加一个新的 content
     * @param content 要添加的 content
     */
    public void insert(Content content);

    /**
     * 删除一个 content
     * @param id 要删除的 content 的 ID
     */
    public void delete(Long id);

    /**
     * 获取一个 content
     * @param id 要获取的 tbUser 的 ID
     */
    public Content getContentById(Long id);

    /**
     * 修改 content 的信息
     * @param content 保存修改信息的 content
     */
    public void update(Content content);

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
    public List<Content> getPage(Map<String, Object> map);

    /**
     * 返回数据库的总记录数
     * @return
     */
    public int count(Content content);
}
