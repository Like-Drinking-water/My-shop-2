package com.huanleichen.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

public interface BaseTreeDao<T extends BaseEntity> {
    /**
     * 获取所有的 数据
     * @return 返回一个 TbUser 的 List 集合
     */
    public List<T> selectAll();

    /**
     * 添加一个新的 entity
     * @param entity 要添加的 entity
     */
    public void insert(T entity);

    /**
     * 删除一个 entity
     * @param id 要删除的 entity 的 ID
     */
    public void delete(Long id);

    /**
     * 获取一个 entity
     * @param id 要获取的 entity 的 ID
     */
    public T getById(Long id);

    /**
     * 修改 entity 的信息
     * @param entity 保存修改信息的 entity
     */
    public void update(T entity);

    /**
     * 通过父节点的 id 找到对应的子节点
     * @param parentId
     * @return
     */
    public List<T> selectByParentId(Long parentId);

}
