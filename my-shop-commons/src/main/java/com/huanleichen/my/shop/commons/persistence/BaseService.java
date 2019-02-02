package com.huanleichen.my.shop.commons.persistence;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;

import java.util.List;


public interface BaseService<T extends BaseEntity> {
    /**
     * 获取所有的数据
     *
     * @return entity 的 List 集合
     */
    public List<T> selectAll();

    /**
     * 删除一个 entity
     *
     * @param id 要删除的 entity 的 ID
     */
    public void delete(Long id);

    /**
     * 获取一个 entity
     *
     * @param id 要获取的 entity 的 ID
     */
    public T getById(Long id);

    /**
     * 修改 entity 的信息
     *
     * @param entity 保存修改信息的 entity
     */
    public void update(T entity);


    /**
     * 保存提交的 entity 信息
     *
     * @param entity 提交的 entity 信息
     * @return 操作的结果
     */
    public BaseResult save(T entity);

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
    public PageInfo<T> getPage(int start, int length, int draw, T entity);


}
