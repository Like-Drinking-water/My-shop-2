package com.huanleichen.my.shop.commons.persistence;

import com.huanleichen.my.shop.commons.dto.BaseResult;

import java.util.List;

public interface OriginService<T extends BaseEntity> {
    /**
     * 获取所有的 数据
     * @return 返回一个 TbUser 的 List 集合
     */
    public List<T> selectAll();

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
     * 保存新的节点
     * @param entity
     * @return
     */
    public BaseResult save(T entity);

}
