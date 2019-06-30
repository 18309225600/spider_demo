package com.lhf.spider;

import com.lhf.spider.entity.po.Content;
import com.lhf.spider.service.DownloadHtmlService;
import lombok.extern.slf4j.Slf4j;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 服务启动后执行任务
 * @author liuhongfei
 * @since v1.0.0
 **/
@Slf4j
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    @Qualifier("httpClientDownloadHtmlServiceImpl")
    private DownloadHtmlService downloadHtmlService;

    @Override
    public void run(String... args) throws Exception {
        log.info("服务启动成功，开始执行爬虫任务");
        String url = args[0];
        getStart(url);
    }

    //创建整个过程
    private void getStart(String url) throws IOException, XPatherException {
        Content download = downloadHtmlService.download(url);
        String htmlContent = download.getHtmlContent();
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode clean = htmlCleaner.clean(htmlContent);
        Object[] objects = clean.evaluateXPath("//*[@id=\"1829\"]");
        if (objects.length>0){
            System.out.println(((TagNode)objects[0]).getText().toString());
        }
    }
}
