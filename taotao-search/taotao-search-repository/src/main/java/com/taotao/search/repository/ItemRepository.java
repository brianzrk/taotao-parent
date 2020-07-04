package com.taotao.search.repository;

import com.taotao.search.pojo.SearchItemDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangrk
 * @date 2020/6/13
 */
@Repository
public interface ItemRepository extends ElasticsearchRepository<SearchItemDocument, Long> {

    //符合jpa命名规范的接口

}