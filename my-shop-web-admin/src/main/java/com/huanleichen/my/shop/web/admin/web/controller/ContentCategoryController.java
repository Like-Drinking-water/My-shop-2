package com.huanleichen.my.shop.web.admin.web.controller;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.domain.ContentCategory;
import com.huanleichen.my.shop.web.admin.service.ContentCategoryService;
import com.huanleichen.my.shop.web.admin.service.abstracts.AbstractBaseTreeController;
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
public class ContentCategoryController extends AbstractBaseTreeController<ContentCategory, ContentCategoryService> {

    @ModelAttribute
    public ContentCategory getContentCategory(Long id) {
        return super.getEntity(id, ContentCategory.class);
    }

    @Override
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

    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data", method = RequestMethod.POST)
    public List<ContentCategory> tree(Long id) {
        return super.tree(id);
    }

    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(ContentCategory contentCategory) {
        return "content_category_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(ContentCategory contentCategory, Model model, RedirectAttributes redirectAttributes) {
       return super.save(contentCategory, model, redirectAttributes, "list", "content_category_form");
    }


}
