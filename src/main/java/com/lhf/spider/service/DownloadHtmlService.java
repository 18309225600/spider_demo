package com.lhf.spider.service;

import com.lhf.spider.entity.po.Content;

import java.io.IOException;

/**
 * 下载html页面Service
 * @author liuhongfei
 * @since v1.0.0
 **/
public interface DownloadHtmlService {

    Content download(String url) throws IOException;
}
