package com.huanleichen.my.shop.web.admin.service.abstracts;

import com.huanleichen.my.shop.commons.persistence.BaseTreeEntity;
import com.huanleichen.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public abstract class AbstractBaseTreeController<T extends BaseTreeEntity, S extends BaseTreeService<T>> extends AbstactOriginController<T, S> {

    /**
     * 信息列表
     * @param model
     * @return
     */
    public abstract String list(Model model);

    /**
     * 返回数据
     * @param id
     * @return
     */
    public List<T> tree(Long id) {
        if (id == null) {
            id = 0L;
        }

        return service.selectByParentId(id);
    }

    /**
     * 表单页
     * @param entity
     * @return
     */
    public abstract String form(T entity);

    /**
     * 通过递归算法对源数据进行排序
     * @param source
     * @param target
     * @param parentId
     */
    protected void sortList(List<T> source, List<T> target, Long parentId) {
        for (T aSource: source) {
            if (aSource.getParent().getId().equals(parentId)) {
                target.add(aSource);

                if (aSource.getIsParent()) {
                    sortList(source, target, aSource.getId());
                }
            }
        }
    }
}
