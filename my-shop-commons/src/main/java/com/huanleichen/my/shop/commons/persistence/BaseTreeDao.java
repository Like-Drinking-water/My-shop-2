package com.huanleichen.my.shop.commons.persistence;

import java.util.List;

public interface BaseTreeDao<T extends BaseTreeEntity> extends OriginDao<T> {

    /**
     * 通过父节点的 id 找到对应的子节点
     * @param parentId
     * @return
     */
    public List<T> selectByParentId(Long parentId);

}
