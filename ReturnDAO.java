package com.example.demo;

import org.springframework.data.elasticsearch.core.SearchHits;

public class ReturnDAO {
	
	SearchHits<Page1> title;
	SearchHits<Page1> heading;
	SearchHits<Page1> text;
	SearchHits<Page1> images;
	
	
	ReturnDAO(){
		
	}
	public ReturnDAO(SearchHits<Page1> title, SearchHits<Page1> heading, SearchHits<Page1> text, SearchHits<Page1> images) {
		super();
		this.title = title;
		this.heading = heading;
		this.text = text;
		this.images = images;
	}
	public SearchHits<Page1> getTitle() {
		return title;
	}
	public void setTitle(SearchHits<Page1> title) {
		this.title = title;
	}
	public SearchHits<Page1> getHeading() {
		return heading;
	}
	public void setHeading(SearchHits<Page1> heading) {
		this.heading = heading;
	}
	public SearchHits<Page1> getText() {
		return text;
	}
	public void setText(SearchHits<Page1> text) {
		this.text = text;
	}
	public SearchHits<Page1> getImages() {
		return images;
	}
	public void setImages(SearchHits<Page1> images) {
		this.images = images;
	}
	
}
