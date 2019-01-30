package com.huanleichen.my.shop.web.admin.service.impl;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.commons.validator.BeanValidator;
import com.huanleichen.my.shop.domain.Content;
import com.huanleichen.my.shop.web.admin.dao.ContentDao;
import com.huanleichen.my.shop.web.admin.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentDao dao;

    @Override
    public List<Content> selectAll() {
        return dao.selectAll();
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Content getContentById(Long id) {
        return dao.getContentById(id);
    }

    @Override
    public void update(Content content) {
        dao.update(content);
    }

    @Override
    public BaseResult save(Content content) {
        BaseResult baseResult = null;
        String errorString = BeanValidator.validator(content);
        if (StringUtils.isBlank(errorString)) {
            baseResult = BaseResult.successResult();
        }
        else {
            baseResult = BaseResult.failResult(errorString);
        }

        //如果用户的信息格式填写正确
        if (baseResult.getStatus() == BaseResult.SUCCESS_STATUS) {
            content.setUpdated(new Date());
            if (content.getId() == null) {
                content.setCreated(new Date());
                dao.insert(content);
                baseResult.setMessage("添加成功");
            } else {
                dao.update(content);
                baseResult.setMessage("修改成功");
            }
        }

        return baseResult;
    }

    @Override
    public void deleteMulti(String[] ids) {
        dao.deleteMulti(ids);
    }

    @Override
    public PageInfo<Content> getPage(int start, int length, int draw, Content content) {
        int count = dao.count(content);
        Map<String, Object> map = new HashMap<>();

        map.put("start", start);
        map.put("length", length);
        map.put("draw", draw);
        map.put("content", content);
        List<Content> data = dao.getPage(map);

        PageInfo<Content> page = new PageInfo<>();
        page.setDraw(draw);
        page.setRecordsFiltered(count);
        page.setRecordsTotal(count);
        page.setData(data);

        return page;
    }

}
