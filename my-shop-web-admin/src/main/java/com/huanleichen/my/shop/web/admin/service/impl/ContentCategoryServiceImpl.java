package com.huanleichen.my.shop.web.admin.service.impl;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.validator.BeanValidator;
import com.huanleichen.my.shop.domain.ContentCategory;
import com.huanleichen.my.shop.web.admin.dao.ContentCategoryDao;
import com.huanleichen.my.shop.web.admin.service.ContentCategoryService;
import com.huanleichen.my.shop.web.admin.service.abstracts.AbstractBaseTreeServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<ContentCategory, ContentCategoryDao> implements ContentCategoryService {

    @Override
    public BaseResult save(ContentCategory entity) {
        BaseResult baseResult = null;
        String erroString = BeanValidator.validator(entity);

        if (erroString == null) {
            //根目录
            if (entity.getParent() == null || entity.getParent().getId() == null) {
                //将其父目录的 ID 设为 0
                entity.getParent().setId(0L);
                entity.setIsParent(true);
            }
            else {
                //获取其父目录，并将其父目录的 isParent 设置为 true
                ContentCategory currentCategoryParent = dao.getById(entity.getParent().getId());
                currentCategoryParent.setIsParent(true);
                update(currentCategoryParent);

                entity.setIsParent(false);
            }
            entity.setUpdated(new Date());
            //新增
            if (entity.getId() == null) {
                entity.setCreated(new Date());
                dao.insert(entity);
                baseResult = BaseResult.successResult("添加新内容成功");
            }
            //修改
            else {
                update(entity);
                baseResult = BaseResult.successResult("修改内容信息成功");
            }


        }
        else {
            baseResult = BaseResult.failResult(erroString);
        }


        return baseResult;
    }


}
