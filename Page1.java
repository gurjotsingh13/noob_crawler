package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
@Document(indexName = "page")
public class Page1 {
		@Id 
		String link;
		String text;
		ArrayList<String> heading;
		String ParentUrl;
		String html;
		String title;
		ArrayList<String> absoluteUrls;
		ArrayList<String> srcValues;
		
		
		
	
		public ArrayList<String> getAbsoluteUrls() {
			return absoluteUrls;
		}
		public void setAbsoluteUrls(ArrayList<String> absoluteUrls) {
			this.absoluteUrls = absoluteUrls;
		}
		public ArrayList<String> getSrcValues() {
			return srcValues;
		}
		public void setSrcValues(ArrayList<String> srcValues) {
			this.srcValues = srcValues;
		}
		public String getParentUrl() {
			return ParentUrl;
		}
		public void setParentUrl(String parentUrl) {
			ParentUrl = parentUrl;
		}
		
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
		public ArrayList<String> getHeading() {
			return heading;
		}
		public void setHeading(ArrayList<String> heading) {
			this.heading = heading;
		}
		public String getHtml() {
			return html;
		}
		public void setHtml(String html) {
			this.html = html;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		
}
