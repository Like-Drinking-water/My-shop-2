package com.huanleichen.my.shop.commons.persistence;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;

import java.util.List;


public interface BaseService<T extends BaseEntity> extends OriginService<T> {
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
