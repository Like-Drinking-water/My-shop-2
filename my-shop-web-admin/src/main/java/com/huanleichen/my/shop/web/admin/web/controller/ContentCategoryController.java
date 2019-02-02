package com.huanleichen.my.shop.web.admin.web.controller;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.domain.ContentCategory;
import com.huanleichen.my.shop.web.admin.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService service;

    @ModelAttribute
    public ContentCategory getContentCategory(Long id) {
        ContentCategory contentCategory = null;
        if (id == null) {
            contentCategory = new ContentCategory();
        }
        else {
            contentCategory = service.getById(id);
            if (contentCategory == null) {
                contentCategory = new ContentCategory();
            }
        }
        return contentCategory;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<ContentCategory> target = new ArrayList<ContentCategory>();
        List<ContentCategory> source = service.selectAll();
        //对源数据进行排序
        sortList(source, target, 0L);
        //将排序完成的集合放到 model 中
        model.addAttribute("contentCategory", target);

        return "content_category_list";
    }

    @ResponseBody
    @RequestMapping(value = "tree/data", method = RequestMethod.POST)
    public List<ContentCategory> tree(Long id) {
        if (id == null) {
            id = 0L;
        }

        return service.selectByParentId(id);
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(ContentCategory contentCategory) {
        return "content_category_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(ContentCategory contentCategory, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(contentCategory);
        //添加成功
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("result", baseResult);
            return "redirect:list";
        }
        //添加失败
        else {
            model.addAttribute("result", baseResult);
            return "content_category_form";
        }
    }

    /**
     * 通过递归算法对源数据进行排序
     * @param source
     * @param target
     * @param parentId
     */
    private void sortList(List<ContentCategory> source, List<ContentCategory> target, Long parentId) {
        for (ContentCategory content: source) {
            if (content.getParent().getId().equals(parentId)) {
                target.add(content);

                if (content.getIsParent()) {
                    sortList(source, target, content.getId());
                }
            }
        }
    }
}
