package com.hua.store.search.controller;

import com.hua.store.common.pojo.Result;
import com.hua.store.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class ItemController {

    @Autowired
    private ItemService service;

    @RequestMapping("/manager/importall")
    @ResponseBody
    public Result importItems() throws IOException, SolrServerException {
        return service.importItems();
    }
}
