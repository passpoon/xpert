package com.crossent.monitoring.portal.common.lib.elasticsearch;


import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;

public class ElasticsearchTemplate implements ElasticsearchOperations {

    public TransportClient client;

    public ElasticsearchTemplate(TransportClient client){
        this.client = client;
    }


    @Override
    public SearchResponse query(String[] indices,
                                String[] types,
                                SearchType searchType,
                                QueryBuilder query,
                                QueryBuilder postFilter,
                                String sortField,
                                SortOrder sortOrder, Integer from , Integer size) {

        SearchRequestBuilder searchRequestBuilder = null;
        searchRequestBuilder = client.prepareSearch(indices);
        if(types != null){
            searchRequestBuilder = searchRequestBuilder.setTypes(types);
        }
        if(searchType != null){
            searchRequestBuilder.setSearchType(searchType);
        }
        if(query != null){
            searchRequestBuilder.setQuery(query);
        }

        if(postFilter != null){
            searchRequestBuilder.setPostFilter(postFilter);
        }

        if(sortField != null){
            searchRequestBuilder.addSort(sortField, sortOrder);

        }

        if(from != null){
            searchRequestBuilder.setFrom(from);
        }

        if(size != null){
            searchRequestBuilder.setSize(size);
        }






        SearchResponse response  = searchRequestBuilder.get();

        return response;
    }

    public SearchResponse query(String index, String type, QueryBuilder query, QueryBuilder postFilter, String sortField,  SortOrder sortOrder, Integer from , Integer size){
        return this.query(new String[]{index}, new String[]{type}, SearchType.DFS_QUERY_THEN_FETCH, query, postFilter, sortField, sortOrder, from , size);
    }

    public SearchResponse query(String index, String type, QueryBuilder query, String sortField,  SortOrder sortOrder, Integer from , Integer size){
        return this.query(new String[]{index}, new String[]{type}, SearchType.DFS_QUERY_THEN_FETCH, query, null, sortField, sortOrder, from, size);
    }

    public SearchResponse query(String index, String type, QueryBuilder query , Integer from , Integer size){
        return this.query(new String[]{index}, new String[]{type}, SearchType.DFS_QUERY_THEN_FETCH, query, null, null, null, from, size);
    }


}
