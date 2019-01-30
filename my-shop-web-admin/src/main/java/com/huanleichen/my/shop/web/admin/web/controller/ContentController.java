package com.huanleichen.my.shop.web.admin.web.controller;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.domain.Content;
import com.huanleichen.my.shop.web.admin.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ContentController {
    @Autowired
    private ContentService service;

    @ModelAttribute
    public Content getContent(Long id) {
        Content content = null;

        if (id == null) {
            content = new Content();
        }

        else {
            content = service.getContentById(id);
        }

        return content;
    }

    @RequestMapping(value = {"list"}, method = RequestMethod.GET)
    public String list() {

        //将视图名称返回给 DispatcherServlet
        return "content_list";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "content_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Content content, Model model, RedirectAttributes redirectAttributes) {
        BaseResult result = service.save(content);

        model.addAttribute("result", result);

        if (result.getStatus() == BaseResult.SUCCESS_STATUS) {
            redirectAttributes.addFlashAttribute("result", result);
            return "redirect:content_list";
        } else {
            model.addAttribute("result", result);
            return "content_form";
        }


    }

    @ResponseBody
    @RequestMapping(value = "deleteMulti", method = RequestMethod.POST)
    public BaseResult deleteMulti(String ids) {
        BaseResult baseResult = null;

        if (!StringUtils.isBlank(ids)) {
            String[] deleteArray = ids.split(",");
            service.deleteMulti(deleteArray);
            baseResult = BaseResult.successResult("删除成功");
        }

        else {
            baseResult = BaseResult.failResult("删除失败");
        }

        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<Content> page(HttpServletRequest request, Content content) {

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw != null? Integer.valueOf(strDraw) : 0;
        int start = strStart != null? Integer.valueOf(strStart) : 0;
        int length = strLength != null? Integer.valueOf(strLength) : 10;

        return service.getPage(start, length, draw, content);

    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "content_detail";
    }

}
