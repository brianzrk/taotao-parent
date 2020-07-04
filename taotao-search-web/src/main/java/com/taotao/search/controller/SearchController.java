package com.taotao.search.controller;

import com.github.pagehelper.Page;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.SearchService;
import com.taotao.search.pojo.SearchItemDocument;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zhangrk
 * @date 2020/6/12
 */
@Controller
public class SearchController {


    @Reference
    SearchService searchService;

    @RequestMapping("/search")
    public String search(HttpServletRequest request
            , @RequestParam(value = "q") String queryString
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "12") Integer limit) {


        Page<SearchItemDocument> searchResult = searchService.search(queryString, page, limit);

        request.setAttribute("query", queryString);
        request.setAttribute("totalPages", searchResult.getPages());
        request.setAttribute("itemList", searchResult.getResult());
        request.setAttribute("page", page);
        return "search";

    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("/initEs")
    @ResponseBody
    public TaotaoResult initEs(HttpServletRequest request, HttpServletResponse response) {
        long totalCount = searchService.initEs();

        response.setHeader("Access-control-Allow-Origin", "*");

        return TaotaoResult.success("初始化 " + totalCount + " 条数据");
    }

}