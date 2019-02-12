package com.huanleichen.my.shop.web.ui.api;

import com.huanleichen.my.shop.commons.utils.HttpClientUtils;

public class ContentAPI {
    public static String getByCategoryId(Long id) {
        return HttpClientUtils.doGet(API.CONTENT_PATH + id);
    }
}
