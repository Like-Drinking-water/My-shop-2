package com.huanleichen.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseEntity> extends OriginDao<T> {

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
    public List<T> getPage(Map<String, Object> map);

    /**
     * 返回数据库的总记录数
     * @return
     */
    public int count(T entity);
}
