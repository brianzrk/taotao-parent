package com.taotao.search.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.search.SearchService;
import com.taotao.search.pojo.SearchItemDocument;
import com.taotao.search.repository.ItemRepository;
import com.taotao.service.ItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangrk
 * @date 2020/6/12
 */
@Service(timeout = 10000, interfaceClass = SearchService.class)
public class SearchServiceImpl implements SearchService {


    @Autowired
    ItemRepository itemRepository;

    @Reference
    ItemService itemService;


    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<SearchItemDocument> search(String queryString, Integer page, Integer limit) {

        Pageable pageable = PageRequest.of(page,limit);
        MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(queryString,
                "title", "sell_point", "category_name", "item_desc");


//        PageHelper.startPage(page, limit);
        org.springframework.data.domain.Page<SearchItemDocument> documents =
                itemRepository.search(queryBuilder, pageable);
//        Iterable<SearchItemDocument> documents = documents;

        List<SearchItemDocument> documentsContent = documents.getContent();
        Page<SearchItemDocument> result = new Page<>();

        result.addAll(documentsContent);

        return result;
    }

    @Override
    public long initEs() {

        int size = 10000;

        EasyUIDataGridResult itemListResult;
        List<TbItem> itemList;

        Long totalCount = 0L;


        for (int i = 1; i < 1000; i++) {
            List<SearchItemDocument> documents = new ArrayList<>();

            itemListResult = itemService.getItemList(i, size);

            itemList = itemListResult.getRows();

            for (TbItem item : itemList) {

                SearchItemDocument document = modelMapper.map(item, SearchItemDocument.class);

                documents.add(document);
            }

            itemRepository.saveAll(documents);

            totalCount += itemList.size();

            if (itemList.size() < size) {
                break;
            }


        }



        return totalCount;
    }
}