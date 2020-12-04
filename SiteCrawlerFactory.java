package com.example.demo;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

@Component
public class SiteCrawlerFactory implements CrawlController.WebCrawlerFactory {


    @Autowired
    private ObjectFactory<SiteCrawler> beanFactory;
	@Override
	public WebCrawler newInstance() throws Exception {
		// TODO Auto-generated method stub
		return beanFactory.getObject();
	}

}