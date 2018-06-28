package com.hua.store.search.service;


import com.hua.store.common.pojo.Result;
import com.hua.store.search.mapper.ItemSearchMapper;
import com.hua.store.search.pojo.Item;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemSearchMapper mapper;

    @Autowired
    private HttpSolrClient client;

    @Override
    public Result importItems() throws SolrServerException, IOException {

        List<Item> items = mapper.getItems();

        for (Item item : items) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSellPoint());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategoryName());
            client.add(document);
        }
        client.commit();
        return Result.OK();


    }
}
