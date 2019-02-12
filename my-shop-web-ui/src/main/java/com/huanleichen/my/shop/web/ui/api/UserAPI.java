package com.huanleichen.my.shop.web.ui.api;

import com.huanleichen.my.shop.commons.utils.HttpClientUtils;
import com.huanleichen.my.shop.commons.utils.MapperUtils;
import com.huanleichen.my.shop.web.ui.dto.TbUserDTO;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserAPI {
    public static TbUserDTO login(TbUserDTO tbUserDTO) throws IOException {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("email", tbUserDTO.getEmail()));
        params.add(new BasicNameValuePair("password", tbUserDTO.getPassword()));
        String result = HttpClientUtils.doPost(API.UESR_PATH_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));
        return MapperUtils.json2pojoByTree(result, "data", TbUserDTO.class);
    }
}
