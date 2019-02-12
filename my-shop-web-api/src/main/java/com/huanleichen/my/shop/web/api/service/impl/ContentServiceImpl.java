package com.huanleichen.my.shop.web.api.service.impl;

import com.huanleichen.my.shop.domain.Content;
import com.huanleichen.my.shop.web.api.dao.ContentDao;
import com.huanleichen.my.shop.web.api.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentDao dao;

    @Override
    public List<Content> selectByCategoryId(Long id) {
        return dao.selectByCategoryId(id);
    }
}
