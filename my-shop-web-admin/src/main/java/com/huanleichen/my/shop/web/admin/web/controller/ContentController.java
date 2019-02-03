package com.huanleichen.my.shop.web.admin.web.controller;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.domain.Content;
import com.huanleichen.my.shop.web.admin.service.ContentService;
import com.huanleichen.my.shop.web.admin.service.abstracts.AbstractBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "content")
public class ContentController extends AbstractBaseController<Content, ContentService> {

    @ModelAttribute
    public Content getContent(Long id) {
       return super.getEntity(id, Content.class);
    }

    @Override
    @RequestMapping(value = {"list"}, method = RequestMethod.GET)
    public String list() {

        //将视图名称返回给 DispatcherServlet
        return "content_list";
    }

    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "content_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Content content, Model model, RedirectAttributes redirectAttributes) {
        return super.save(content, model, redirectAttributes, "list", "content_form");
    }

    @ResponseBody
    @RequestMapping(value = "deleteMulti", method = RequestMethod.POST)
    public BaseResult deleteMulti(String ids) {
        return super.deleteMulti(ids);
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<Content> page(HttpServletRequest request, Content content) {

        return super.page(request, content);

    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "content_detail";
    }

}
