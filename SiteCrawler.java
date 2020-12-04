package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



@Component
public class SiteCrawler extends WebCrawler {

	@Autowired
	SavingService s1;
	
	private final static Pattern EXCLUSIONS = Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|))$");

	public static int nthOccurrence(String str1, String str2, int n, boolean ignoreCase) { 
        if(ignoreCase) {
            str1 = str1.toLowerCase();
            str2 = str2.toLowerCase();
        }
        
        String tempStr = str1;
        int tempIndex = -1;
        int finalIndex = 0;
        for(int occurrence = 0; occurrence < n ; ++occurrence){
            tempIndex = tempStr.indexOf(str2);
            if(tempIndex==-1){
                finalIndex = 0;
                break;
            }
            tempStr = tempStr.substring(++tempIndex);
            finalIndex+=tempIndex;
        }
        return --finalIndex;
    } 
	
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
	    String urlString = url.getURL().toLowerCase();
	    String finalurls = s1.getStartingSeed();
	    int index = nthOccurrence(finalurls,"/", 3, true);
	    String finalLink = finalurls.substring(0,index);
	    System.out.println(finalLink);
	    if((urlString.startsWith(finalLink)) && !EXCLUSIONS.matcher(urlString).matches()) {
            return true;
        } else {
            return false;
        }
	}
	
	@Override
	public void visit(Page page) {
		String parentUrl  = page.getWebURL().getParentUrl();
	    String url = page.getWebURL().getURL();
	    String title = null;
	    String text = null;
	    String htmlString = null;
	    Set<WebURL> links = null;
	    ArrayList<String> h1= new ArrayList<>();
	    ArrayList<String> absoluteUrls = new ArrayList<>();
	    ArrayList<String> srcValues = new ArrayList<>();
	    
	    //  List<String> absoluteUrl = new ArrayList<>();
	    
	    if (page.getParseData() instanceof HtmlParseData) {
	        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
	        
	         title= htmlParseData.getTitle().replaceAll("\n", "").replaceAll("\t", "");
	         text= htmlParseData.getText().replaceAll("\n", "").replaceAll("\t", "");
	         htmlString= htmlParseData.getHtml().replaceAll("\n", "").replaceAll("\r", "");
	         Document html = Jsoup.parse(htmlString);
	         h1.add(html.body().getElementsByTag("h1").text());
	         h1.add(html.body().getElementsByTag("h2").text());
	         h1.add(html.body().getElementsByTag("h3").text());
	         h1.add(html.body().getElementsByTag("h4").text());
	         h1.add(html.body().getElementsByTag("h5").text());
	         
	         Elements imageElement = html.select("img");
	         for(Element i: imageElement) {
	        	 String absoluteUrl  = i.absUrl("src");
	        	 String srcValue = i.attr("src");
	        	 absoluteUrls.add(absoluteUrl);
	        	 srcValues.add(srcValue);
	        	 }
	         links= htmlParseData.getOutgoingUrls(); 
	        // do something with the collected data
	    }
	    Page1 d1 = new Page1();
	    d1.setParentUrl(parentUrl);
        d1.setLink(url);
        d1.setTitle(title);
        d1.setHtml(htmlString);
        d1.setHeading(h1);
        d1.setText(text);
        d1.setAbsoluteUrls(absoluteUrls);
        d1.setSrcValues(srcValues);
        s1.save(d1);
	   
	}
}
