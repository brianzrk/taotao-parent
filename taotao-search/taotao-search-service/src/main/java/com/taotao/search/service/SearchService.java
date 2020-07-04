package com.taotao.search.service;

import com.taotao.search.pojo.SearchItemDocument;

import java.util.List;

/**
 * @author brian
 * @date 2020/6/27
 */
public interface SearchService {

    List<SearchItemDocument> search(String queryString, Integer page, Integer limit);

}