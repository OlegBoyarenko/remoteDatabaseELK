package com.packagename.myapp.view;

import com.alibaba.fastjson.JSON;
import com.packagename.myapp.component.connect.Elastic;
import com.packagename.myapp.model.Customer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page with ELK grid,
 */
@Route("elastic")
@ComponentScan
public class ElasticView extends VerticalLayout {
    Grid<Customer> CustomerGrid = new Grid<>(Customer.class);
    private final RestHighLevelClient client;
    HorizontalLayout horizontalLayout;
    TextArea keyWord = new TextArea("key word");
    Button findMatches = new Button("find by key word");

    public ElasticView(Elastic elastic) {
        client = elastic.getClient();

        horizontalLayout = new HorizontalLayout();

        findMatches.addClickListener(click -> {
            try {
                search(keyWord.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        add(CustomerGrid, findMatches, keyWord);

    }

    /**
     *
     * @param key search param
     * @throws IOException
     * Search by Data field in ELK index. Find matches and set to entity to grid
     */
    public void search(String key) throws IOException {
        /**
         * Create search request. Find any matches. Data to lower case
         */
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .postFilter(QueryBuilders.simpleQueryStringQuery("+" + key));

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);

        /**
         * All Hits to collection
         */
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = response.getHits()
                .getHits();
        List<Customer> results = Arrays.stream(searchHits)
                .map(hit -> JSON.parseObject(hit.getSourceAsString(), Customer.class))
                .collect(Collectors.toList());

        CustomerGrid.setItems(results);
    }
}

