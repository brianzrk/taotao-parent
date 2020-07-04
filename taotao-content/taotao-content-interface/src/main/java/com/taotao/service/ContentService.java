package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * @author zhangrk
 * @date 2020/5/28
 */
public interface ContentService {

    TaotaoResult saveContent(TbContent content);

    List<TbContent> getContentByCategoryId(Long categoryId);

}