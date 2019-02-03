package com.huanleichen.my.shop.web.admin.service.abstracts;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.commons.persistence.BaseEntity;
import com.huanleichen.my.shop.commons.persistence.BaseService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController<T extends BaseEntity, S extends BaseService<T>>
    extends AbstactOriginController<T, S> {

    /**
     * 列表页
     * @return
     */
    public abstract String list();

    /**
     * 表单页
     * @return
     */
    public abstract String form();

    /**
     * 批量删除
     * @param ids
     * @return
     */
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

    /**
     *
     * @param request
     * @param entity
     * @return
     */
    public PageInfo<T> page(HttpServletRequest request, T entity) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw != null? Integer.valueOf(strDraw) : 0;
        int start = strStart != null? Integer.valueOf(strStart) : 0;
        int length = strLength != null? Integer.valueOf(strLength) : 10;

        return service.getPage(start, length, draw, entity);
    }

}
