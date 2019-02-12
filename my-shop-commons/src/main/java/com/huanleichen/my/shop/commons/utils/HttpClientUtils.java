package com.huanleichen.my.shop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class HttpClientUtils {
    private static final String GET = "get";
    private static final String POST = "post";
    private static final String REQUEST_CONNECTION = "keep-alive";
    private static final String REQUEST_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String doGet(String url) {
        return doMethod(GET, url, null);
    }

    public static String doGet(String url, String cookie) {
        return doMethod(GET, url, cookie);
    }

    public static String doPost(String url, BasicNameValuePair... params) {
        return doMethod(POST, url, null, params);
    }

    public static String doPost(String url, String cookie, BasicNameValuePair... params) {
        return doMethod(POST, url, cookie, params);
    }

    private static String doMethod(String method, String url, String cookie, BasicNameValuePair... params) {
        String data = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        try {
            HttpGet get = null;
            HttpPost post = null;

            //Get 请求
            if (method.equals(GET)) {
                get = new HttpGet(url);
                get.setHeader("Cookie", cookie);
                get.setHeader("Connection", REQUEST_CONNECTION);
                get.setHeader("User-agent", REQUEST_USER_AGENT);
                httpResponse = httpClient.execute(get);
              }
            //Post 请求
            else {
                post = new HttpPost(url);
                post.setHeader("Cookie", cookie);
                post.setHeader("Connection", REQUEST_CONNECTION);
                post.setHeader("User-agent", REQUEST_USER_AGENT);
                //如果有参数则设置参数
                if (params != null && params.length > 0) {
                    post.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), "UTF-8"));
                    httpResponse = httpClient.execute(post);
                }
            }

            HttpEntity httpEntity = httpResponse.getEntity();
            data = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
    }
}
