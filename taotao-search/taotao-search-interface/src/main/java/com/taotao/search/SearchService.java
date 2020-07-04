package com.taotao.search;


import com.github.pagehelper.Page;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.pojo.SearchItemDocument;

import java.util.List;

/**
 * @author zhangrk
 * @date 2020/6/12
 */
public interface SearchService {

    Page<SearchItemDocument> search(String queryString, Integer page, Integer limit);

    long initEs();
}