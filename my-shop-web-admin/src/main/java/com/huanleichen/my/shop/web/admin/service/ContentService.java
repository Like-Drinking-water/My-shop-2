package com.huanleichen.my.shop.web.admin.service;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.domain.Content;

import java.util.List;

public interface ContentService {
    /**
     * 获取所有的 Content
     *
     * @return Content 的 List 集合
     */
    public List<Content> selectAll();

    /**
     * 删除一个 Content
     *
     * @param id 要删除的 Content 的 ID
     */
    public void delete(Long id);

    /**
     * 获取一个 Content
     *
     * @param id 要获取的 Content 的 ID
     */
    public Content getContentById(Long id);

    /**
     * 修改 content 的信息
     *
     * @param content 保存修改信息的 content
     */
    public void update(Content content);

    /**
     * 保存提交的 content 信息
     *
     * @param content 提交的 content 信息
     * @return 操作的结果
     */
    public BaseResult save(Content content);

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteMulti(String[] ids);

    /**
     * 获取分页
     * @param start 开始数据
     * @param length 获取长度
     * @return
     */
    public PageInfo<Content> getPage(int start, int length, int draw, Content content);
}
