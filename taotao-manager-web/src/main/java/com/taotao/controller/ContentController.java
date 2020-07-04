package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangrk
 * @date 2020/5/31
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentService;


    public TaotaoResult saveContent() {

        return TaotaoResult.fail("test");
    }

    @GetMapping("/query/list")
    @ResponseBody
    public List<TbContent> getContentByParentId(
            @RequestParam(value = "categoryId", defaultValue = "0") Long parentId) {
        List<TbContent> contentByCategoryId = contentService.getContentByCategoryId(parentId);
        return contentByCategoryId;
    }


}