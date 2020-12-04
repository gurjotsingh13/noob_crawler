package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.CrawlController.WebCrawlerFactory;

@RestController
@RequestMapping("/mySpider")
public class SiteUIcontroller {
	
	@Autowired
	SavingService repository;
	
	@Autowired 
	WebCrawlerFactory<SiteCrawler> s1;
	
	@PostMapping()
	public String AddSeedUrl(@RequestBody String s) throws Exception {
		CrawlController controller = SiteCrawlControllerFactory.getController();
		repository.setStartingSeed(s);
		controller.addSeed(s);
		CrawlController.WebCrawlerFactory<SiteCrawler> factory = s1;
		controller.startNonBlocking(factory, 20);
		controller.waitUntilFinish();

		return "Url Added";
	}
	
	@GetMapping("/search/{s}")
	public ReturnDAO SearchFor(@PathVariable String s){
		System.out.println(s);
		return repository.getPageWithMatch(s);
	}
}
