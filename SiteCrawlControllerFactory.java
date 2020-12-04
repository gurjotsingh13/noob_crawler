package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class SiteCrawlControllerFactory {

	private final static String CRAWL_STORAGE = "/data/crawl/root";
   static RobotstxtConfig robotstxtConfig = new RobotstxtConfig();

   public static CrawlController getController() throws Exception {
       CrawlConfig config = prepareConfig();
       PageFetcher pageFetcher = new PageFetcher(config);
       RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
       CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
       return controller;
   }

   private static CrawlConfig prepareConfig() {
       CrawlConfig config = new CrawlConfig();
       config.setCrawlStorageFolder(CRAWL_STORAGE + "/" + RandomStringUtils.random(10));
       config.setUserAgentString(RandomAgentsUtils.nextAgent());
       config.setResumableCrawling(true);
       config.setProxyPort(8080);
       config.setIncludeBinaryContentInCrawling(true);
       config.setThreadShutdownDelaySeconds(2);
       config.setMaxConnectionsPerHost(40);
      // config.setMaxDepthOfCrawling(6);
       config.setThreadMonitoringDelaySeconds(2);
       return config;
   }
}
