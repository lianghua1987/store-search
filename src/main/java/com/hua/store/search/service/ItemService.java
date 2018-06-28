package com.hua.store.search.service;

import com.hua.store.common.pojo.Result;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface ItemService {

    public Result importItems() throws SolrServerException, IOException;
}
