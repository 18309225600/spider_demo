package com.lhf.spider.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 下载页面工具类
 * @author liuhongfei
 * @since v1.0.0
 **/
public class PageDownloadUtils {

    /**
     * 通过URL 通过get的请求方式获取页面的html
     * @param url
     * @return
     */
    public static String getContentHtml(String url) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.custom().build();
        HttpGet getRequest = new HttpGet(url);
        CloseableHttpResponse response = closeableHttpClient.execute(getRequest);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        return content;
    }

}
