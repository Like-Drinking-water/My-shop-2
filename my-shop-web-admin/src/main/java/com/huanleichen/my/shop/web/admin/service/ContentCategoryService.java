package com.huanleichen.my.shop.web.admin.service;

import com.huanleichen.my.shop.commons.persistence.BaseTreeService;
import com.huanleichen.my.shop.domain.ContentCategory;

import java.util.List;

public interface ContentCategoryService extends BaseTreeService<ContentCategory> {

    /**
     * 通过父节点的 id 找到对应的子节点
     * @param parentId
     * @return
     */
    public List<ContentCategory> selectByParentId(Long parentId);
}
