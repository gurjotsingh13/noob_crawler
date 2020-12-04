package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class SavingService {
	@Autowired
	PageRepo elasticRep;
	
	@Autowired
	private ElasticsearchOperations template;
	public void save(Page1 p) {
		elasticRep.save(p);
	}
	
	String StartingSeed;

	public String getStartingSeed() {
		return StartingSeed;
	}

	public void setStartingSeed(String startingSeed) {
		StartingSeed = startingSeed;
	}
	
	public ReturnDAO getPageWithMatch(String query){
		NativeSearchQuery searchQuery ;
		searchQuery = new NativeSearchQueryBuilder()
				  .withQuery(QueryBuilders.multiMatchQuery(query)
				    .field("text")
				    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
				  .withHighlightFields(new HighlightBuilder.Field("text").preTags("<span style='background-color:yellow;font-weight:bold'>").postTags("</span>"))
			
				  .build();
		
		SearchHits<Page1> text1 = template.search(searchQuery, Page1.class);
				
		searchQuery = new NativeSearchQueryBuilder()
				  .withQuery(QueryBuilders.multiMatchQuery(query)
				  .field("heading")
				    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
				  .withHighlightFields(new HighlightBuilder.Field("heading").preTags("<span style='background-color:yellow;font-weight:bold'>").postTags("</span>"))
				  .build();
		
		SearchHits<Page1> heading1 = template.search(searchQuery, Page1.class);
		
		searchQuery = new NativeSearchQueryBuilder()
				  .withQuery(QueryBuilders.multiMatchQuery(query)
				  .field("title")
				    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
				  .withHighlightFields(new HighlightBuilder.Field("title").preTags("<span style='background-color:yellow;font-weight:bold'>").postTags("</span>"))
				  .build();
		
		SearchHits<Page1> title1 = template.search(searchQuery, Page1.class);
		
		searchQuery = new NativeSearchQueryBuilder()
				  .withQuery(QueryBuilders.multiMatchQuery(query)
				  .field("srcValues")
				    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
				  .withHighlightFields(new HighlightBuilder.Field("srcValues").preTags("<span style='background-color:yellow;font-weight:bold'>").postTags("</span>"))
				  .build();
		
		SearchHits<Page1> image1 = template.search(searchQuery, Page1.class);
		
		ReturnDAO r1 = new ReturnDAO(title1, heading1, text1,image1);
		
	    
		return r1;
	}
	
	
}
