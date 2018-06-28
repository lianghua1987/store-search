package com.hua.store.search.service;

import com.hua.store.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface SearchService {

    public SearchResult search(String query, int page, int rows) throws IOException, SolrServerException;
}
