package com.huanleichen.my.shop.web.ui.controller;

import com.huanleichen.my.shop.commons.utils.HttpClientUtils;
import com.huanleichen.my.shop.commons.utils.MapperUtils;
import com.huanleichen.my.shop.web.ui.api.ContentAPI;
import com.huanleichen.my.shop.web.ui.dto.ContentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {
    @RequestMapping(value = {"", "index.html", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        requestPPT(model);
        return "index";
    }

    private void requestPPT(Model model) {
        String result = ContentAPI.getByCategoryId(89L);
        try {
            List<ContentDTO> data = MapperUtils.json2listByNode(result, "data", ContentDTO.class);
            model.addAttribute("ppt", data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
