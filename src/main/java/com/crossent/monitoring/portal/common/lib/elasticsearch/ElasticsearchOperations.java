package com.crossent.monitoring.portal.common.lib.elasticsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

public interface ElasticsearchOperations {


    public SearchResponse query(String[] indices,
                                String[] types,
                                SearchType searchType,
                                QueryBuilder query,
                                QueryBuilder postFilter,
                                String sortField,
                                SortOrder sortOrder, Integer from, Integer size);
}
