package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;

import java.util.List;

/**
 * @author zhangrk
 * @date 2020/5/28
 */
public interface ContentCategoryService {

    List<TbContentCategory> getContentCategoryByParentId(Long parentId);

    List<EasyUITreeNode> getContentCategoryList(Long parentId);

    TaotaoResult createContentCategory(Long parent, String name);

}