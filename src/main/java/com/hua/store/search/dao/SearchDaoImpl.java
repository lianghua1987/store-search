package com.hua.store.search.dao;

import com.hua.store.search.pojo.Item;
import com.hua.store.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private HttpSolrClient client;

    @Override
    public SearchResult search(SolrQuery query) throws IOException, SolrServerException {
        SearchResult result = new SearchResult();

        QueryResponse response = client.query(query);

        SolrDocumentList documentList = response.getResults();
        result.setTotalCount(documentList.getNumFound());


        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        List<Item> items = new ArrayList<>();

        for (SolrDocument doc : documentList) {
            Item item = new Item();
            item.setId((String) doc.get("id"));

            List<String> titleList = highlighting.get((String) doc.get("id")).get("item_title");


            if (titleList != null && !titleList.isEmpty()) {
                item.setTitle(titleList.get(0));
            } else {
                item.setTitle((String) doc.get("item_title"));
            }

            item.setPrice((Long) doc.get("item_price"));
            item.setCategoryName((String) doc.get("item_category_name"));
            item.setSellPoint((String) doc.get("item_sell_point"));


            List<String> sellPointList = highlighting.get((String) doc.get("id")).get("item_sell_point");


            if (sellPointList != null && !sellPointList.isEmpty()) {
                item.setSellPoint(sellPointList.get(0));
            } else {
                item.setSellPoint((String) doc.get("item_sell_point"));
            }


            item.setImage((String) doc.get("item_image"));
            items.add(item);
        }

        result.setItems(items);

        return result;
    }
}
