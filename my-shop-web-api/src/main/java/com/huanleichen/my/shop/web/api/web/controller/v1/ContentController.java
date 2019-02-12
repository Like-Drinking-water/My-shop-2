package com.huanleichen.my.shop.web.api.web.controller.v1;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.domain.Content;
import com.huanleichen.my.shop.web.api.service.ContentService;
import com.huanleichen.my.shop.web.api.web.dto.ContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/contents")
public class ContentController {
    @Autowired
    private ContentService service;

    @RequestMapping(value = "{category_id}", method = RequestMethod.GET)
    public BaseResult find(@PathVariable(value = "category_id") Long id) {
        List<ContentDTO> contentDTOS = new ArrayList<ContentDTO>();
        List<Content> contents =  service.selectByCategoryId(id);

        if (contents != null && contents.size() > 0) {
            for (Content content: contents) {
                ContentDTO contentDTO = new ContentDTO();
                BeanUtils.copyProperties(content, contentDTO);
                contentDTOS.add(contentDTO);
            }
            return BaseResult.successResult("成功", contentDTOS);
        }
        else {
            return BaseResult.failResult("没有找到该内容分类");
        }
    }
}
