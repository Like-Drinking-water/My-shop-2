package com.huanleichen.my.shop.web.admin.service.abstracts;

import com.huanleichen.my.shop.commons.persistence.BaseEntity;
import com.huanleichen.my.shop.commons.persistence.BaseTreeDao;
import com.huanleichen.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity, E extends BaseTreeDao<T>> implements BaseTreeService<T> {
    @Autowired
    protected E dao;

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
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
    public void update(T entity) {
        dao.update(entity);
    }

}
