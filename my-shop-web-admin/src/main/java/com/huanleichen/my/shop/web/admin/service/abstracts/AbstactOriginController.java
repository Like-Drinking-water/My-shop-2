package com.huanleichen.my.shop.web.admin.service.abstracts;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.persistence.BaseEntity;
import com.huanleichen.my.shop.commons.persistence.OriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class AbstactOriginController<T extends BaseEntity, S extends OriginService<T>> {
    @Autowired
    protected S service;

    public T getEntity(Long id, Class<T> clazz) {
        T entity = null;
        try {
            if (id == null) {
                entity = clazz.newInstance();
            }
            else {
                entity = service.getById(id);
                if (entity == null) {
                    entity = clazz.newInstance();
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 保存信息
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public String save(T entity, Model model, RedirectAttributes redirectAttributes, String successUrl, String failUrl) {
        BaseResult result = service.save(entity);

        model.addAttribute("result", result);

        if (result.getStatus() == BaseResult.SUCCESS_STATUS) {
            redirectAttributes.addFlashAttribute("result", result);
            return "redirect:" + successUrl;
        } else {
            model.addAttribute("result", result);
            return failUrl;
        }


    }
}
