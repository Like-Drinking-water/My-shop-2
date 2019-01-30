package com.huanleichen.my.shop.web.admin.service.impl;

import com.huanleichen.my.shop.domain.ContentCategory;
import com.huanleichen.my.shop.web.admin.dao.ContentCategoryDao;
import com.huanleichen.my.shop.web.admin.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private ContentCategoryDao dao;

    @Override
    public List<ContentCategory> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<ContentCategory> selectByParentId(Long parentId) {
        return dao.selectByParentId(parentId);
    }

    @Override
    public ContentCategory getContentCategoryById(Long id) {
        return dao.getContentCategoryById(id);
    }
}
