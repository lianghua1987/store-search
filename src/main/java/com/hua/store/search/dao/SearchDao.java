package com.hua.store.search.dao;

import com.hua.store.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface SearchDao {
    public SearchResult search(SolrQuery query) throws IOException, SolrServerException;
}
