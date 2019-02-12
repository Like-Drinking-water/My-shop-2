package com.huanleichen.my.shop.web.api.service;

import com.huanleichen.my.shop.domain.Content;

import java.util.List;

public interface ContentService {
    /**
     * 通过父级类目的 id 找到所有内容
     * @param id
     * @return
     */
    public List<Content> selectByCategoryId(Long id);
}
