package org.githup.es.springbootes;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryShardContext;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortFieldAndFormat;
import org.elasticsearch.search.sort.SortOrder;
import org.githup.es.SpringBootEsApplication;
import org.githup.es.service.ESSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * 单元测试
 * 
 * @author sdc
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootEsApplication.class)
public class SpringBootEsApplicationTests {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringBootEsApplicationTests.class);

	/**
	 * 搜索服务
	 */
	@Autowired
	private ESSearchService esSearchService;

    @Autowired
	private TransportClient client;

	@Test
	public void testQueryBuilder() {
//        QueryBuilder queryBuilder = QueryBuilders.termQuery("user", "kimchy");
		//QueryBuilders.termsQuery("name", new ArrayList<String>().add("我"));
//        QueryBuilder queryBuilder = QueryBuilders.matchQuery("user", "kimchy");
//        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("kimchy", "user", "message", "gender");
		//QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

		QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "jack");
		searchFunction(queryBuilder);


	}



	private void searchFunction(QueryBuilder queryBuilder) {

		SortBuilder sortBuilder=SortBuilders.fieldSort("age").order(SortOrder.ASC);
		SearchResponse response = client.prepareSearch("blog")
				//.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setTypes("user")
				.setFrom(5)
				.setQuery(queryBuilder)
				.addSort(sortBuilder)
				.setSize(100).execute().actionGet();

		//System.out.println(response);

		for (SearchHit hit : response.getHits()) {
			Map<String, Object> map= hit.getSource();
			System.out.println(map);
		}



/*
		while(true) {
			response = client.prepareSearchScroll(response.getScrollId())
					.setScroll(new TimeValue(60000)).execute().actionGet();
			System.out.println(response);
			for (SearchHit hit : response.getHits()) {
				Iterator<Map.Entry<String, Object>> iterator = hit.getSource().entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry<String, Object> next = iterator.next();
					System.out.println(next.getKey() + ": " + next.getValue());
					if(response.getHits().hits().length == 0) {
						break;
					}
				}
			}
			break;
		}*/
//        testResponse(response);
	}


}
