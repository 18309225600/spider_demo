package com.lhf.spider.service.impl;

import com.lhf.spider.entity.po.Content;
import com.lhf.spider.service.DownloadHtmlService;
import com.lhf.spider.utils.PageDownloadUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author liuhongfei
 * @since v1.0.0
 **/
@Service("httpClientDownloadHtmlServiceImpl")
public class HttpClientDownloadHtmlServiceImpl implements DownloadHtmlService {
    @Override
    public Content download(String url) throws IOException {
        String contentHtml = PageDownloadUtils.getContentHtml(url);
        Content content = new Content();
        content.setHtmlContent(contentHtml);
        return content;
    }
}
