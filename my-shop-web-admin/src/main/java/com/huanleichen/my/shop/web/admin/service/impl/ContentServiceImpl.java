package com.huanleichen.my.shop.web.admin.service.impl;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.validator.BeanValidator;
import com.huanleichen.my.shop.domain.Content;
import com.huanleichen.my.shop.web.admin.dao.ContentDao;
import com.huanleichen.my.shop.web.admin.service.ContentService;
import com.huanleichen.my.shop.web.admin.service.abstracts.AbstractBaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentServiceImpl extends AbstractBaseServiceImpl<Content, ContentDao> implements ContentService {

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


        return super.save(content, baseResult);
    }


}
