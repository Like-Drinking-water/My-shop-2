package com.huanleichen.my.shop.web.admin.service.abstracts;

import com.huanleichen.my.shop.commons.persistence.BaseTreeDao;
import com.huanleichen.my.shop.commons.persistence.BaseTreeEntity;
import com.huanleichen.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public abstract class AbstractBaseTreeServiceImpl<T extends BaseTreeEntity, E extends BaseTreeDao<T>> implements BaseTreeService<T> {
    @Autowired
    protected E dao;

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<T> selectByParentId(Long parentId) {
        return dao.selectByParentId(parentId);
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(T entity) {
        dao.update(entity);
    }

}
