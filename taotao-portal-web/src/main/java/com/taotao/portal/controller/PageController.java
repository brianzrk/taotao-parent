package com.taotao.portal.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.portal.pojo.Ad1Node;
import com.taotao.service.ContentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * taotao-parent-com.taotao.portal.controller
 *
 * @author zhangrk
 * @date 2020/5/23
 */
@Controller
@PropertySource(value={"classpath:resource/resource.properties"})
public class PageController {

    @Reference
    private ContentService contentService;

//    @Value("${AD1_CATEGORY_ID}")
//    private Long categoryId;

    @Value("${AD1_HEIGHT_B}")
    private String AD1_HEIGHT_B;

    @Value("${AD1_HEIGHT}")
    private String AD1_HEIGHT;

    @Value("${AD1_WIDTH}")
    private String AD1_WIDTH;

    @Value("${AD1_WIDTH_B}")
    private String AD1_WIDTH_B;

    private Long bigAdCategoryId = 89L;

    @GetMapping({"/index", "/", ""})
    public String showIndex(HttpServletRequest request) {
        //首页的大广告取自Content的redis缓存

        TbContentExample example = new TbContentExample();
        example.createCriteria();
        List<TbContent> contentList = contentService.getContentByCategoryId(bigAdCategoryId);

        //转成自定义的POJO   AD1NOde的列表
        List<Ad1Node> nodes = new ArrayList<>();
        for (TbContent tbContent : contentList) {
            Ad1Node node = new Ad1Node();
            node.setAlt(tbContent.getSubTitle());
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            node.setHref(tbContent.getUrl());
            node.setSrc(tbContent.getPic());
            node.setSrcB(tbContent.getPic2());
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            nodes.add(node);
        }
        String jsonString = JSON.toJSONString(nodes);
        System.out.println(jsonString);
        request.setAttribute("ad1", JSON.toJSONString(nodes));
        request.getSession().setAttribute("ad1", nodes);
//        model.addAttribute("ad1", JSON.toJSONString(nodes));
//        model.addAttribute("ad1", nodes);
        return "index";
    }
}