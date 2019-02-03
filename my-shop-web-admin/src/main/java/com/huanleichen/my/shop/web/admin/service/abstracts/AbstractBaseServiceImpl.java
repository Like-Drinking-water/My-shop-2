package com.huanleichen.my.shop.web.admin.service.abstracts;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.commons.persistence.BaseDao;
import com.huanleichen.my.shop.commons.persistence.BaseEntity;
import com.huanleichen.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
public abstract class AbstractBaseServiceImpl<T extends BaseEntity, E extends BaseDao<T>> implements BaseService<T> {
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
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMulti(String[] ids) {
        dao.deleteMulti(ids);
    }

    @Override
    public PageInfo<T> getPage(int start, int length, int draw, T entity) {
        int count = dao.count(entity);
        Map<String, Object> map = new HashMap<>();

        map.put("start", start);
        map.put("length", length);
        map.put("draw", draw);
        map.put("entity", entity);
        List<T> data = dao.getPage(map);

        PageInfo<T> page = new PageInfo<T>();
        page.setDraw(draw);
        page.setRecordsFiltered(count);
        page.setRecordsTotal(count);
        page.setData(data);

        return page;
    }

    @Transactional(readOnly = false)
    protected BaseResult save(T entity, BaseResult baseResult) {
        //如果用户的信息格式填写正确
        if (baseResult.getStatus() == BaseResult.SUCCESS_STATUS) {
            entity.setUpdated(new Date());
            if (entity.getId() == null) {
                entity.setCreated(new Date());
                dao.insert(entity);
                baseResult.setMessage("添加成功");
            } else {
                update(entity);
                baseResult.setMessage("修改成功");
            }
        }
        return baseResult;
    }
}
