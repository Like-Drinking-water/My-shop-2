package com.huanleichen.my.shop.web.admin.service.impl;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.validator.BeanValidator;
import com.huanleichen.my.shop.domain.Content;
import com.huanleichen.my.shop.web.admin.dao.ContentDao;
import com.huanleichen.my.shop.web.admin.service.ContentService;
import com.huanleichen.my.shop.web.admin.service.abstracts.AbstractBaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class ContentServiceImpl extends AbstractBaseServiceImpl<Content, ContentDao> implements ContentService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(Content content) {
        BaseResult baseResult = null;
        String errorString = BeanValidator.validator(content);
        if (StringUtils.isBlank(errorString)) {
            baseResult = BaseResult.successResult();
        }
        else {
            baseResult = BaseResult.failResult(errorString);
        }


        return super.save(content, baseResult);
    }


}
