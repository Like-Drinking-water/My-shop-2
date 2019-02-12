package com.huanleichen.my.shop.web.api.dao;

import com.huanleichen.my.shop.domain.Content;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentDao {
    /**
     * 通过父级类目的 id 找到所有内容
     * @param id
     * @return
     */
    public List<Content> selectByCategoryId(Long id);
}
