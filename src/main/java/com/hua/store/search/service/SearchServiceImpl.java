package com.hua.store.search.service;


import com.hua.store.search.dao.SearchDao;
import com.hua.store.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;


    @Override
    public SearchResult search(String query, int page, int rows) throws IOException, SolrServerException {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(query);
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);

        solrQuery.set("df", "item_keywords");

        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("<em>");

        SearchResult result = searchDao.search(solrQuery);

        long totalCount = result.getTotalCount();

        long totalPages = result.getTotalCount() % rows == 0 ? result.getTotalCount() / rows : result.getTotalCount() / rows + 1;

        result.setTotalPages(totalPages);
        result.setCurrentPage(page);

        return result;
    }
}
