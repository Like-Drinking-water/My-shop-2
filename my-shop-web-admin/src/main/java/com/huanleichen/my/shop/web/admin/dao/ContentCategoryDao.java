package com.huanleichen.my.shop.web.admin.dao;

import com.huanleichen.my.shop.domain.ContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentCategoryDao {
    /**
     * 查询全部数据
     * @return
     */
    public List<ContentCategory> selectAll();

    /**
     * 通过父节点的 id 找到对应的子节点
     * @param parentId
     * @return
     */
    public List<ContentCategory> selectByParentId(Long parentId);

    /**
     * 通过 ID 获取内容分类
     * @param id
     * @return
     */
    public ContentCategory getContentCategoryById(Long id);
}
