package com.hua.store.search.controller;

import com.hua.store.common.pojo.Result;
import com.hua.store.common.utils.ExceptionUtil;
import com.hua.store.search.service.SearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class SearchController {

    @Autowired
    private SearchService service;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Result search(@RequestParam("q") String query, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "60") Integer rows) {

            if (StringUtils.isEmpty(query)) {
            return Result.build(400, "query must not be empty");
        }

        try {
            return Result.OK(service.search(new String(query.getBytes("iso8859-1"), "utf-8"), page, rows));
        } catch (IOException | SolrServerException e) {
            return Result.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

}
