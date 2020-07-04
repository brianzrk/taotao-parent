package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentCategoryService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangrk
 * @date 2020/5/31
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Reference
    private ContentCategoryService categoryService;

    @GetMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getCategoryByParentId(
            @RequestParam(value = "id", defaultValue = "0") Long parentId) {
        return categoryService.getContentCategoryList(parentId);
    }
}