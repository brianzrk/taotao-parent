package com.taotao.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author zhangrk
 * @date 2020/5/28
 */
@Component
@Service(timeout = 10000, interfaceClass = ContentService.class)
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public TaotaoResult saveContent(TbContent content) {

        content.setCreated(new Date());
        content.setUpdated(content.getCreated());
        contentMapper.insert(content);
        return TaotaoResult.success();
    }

    @Override
    public List<TbContent> getContentByCategoryId(Long categoryId) {

        String key = "TB_CONTENT_KEY";

        try {
            List<TbContent> contents = (List<TbContent>) redisService.hget(key, categoryId + "");
            if (contents != null) {
                System.out.println("========>从缓存中获取内容-大广告");
                return contents;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = contentMapper.selectByExample(example);
        System.out.println("===========>缓存中没有数据，从数据库中查出大广告，并添加缓存");
        try {
            redisService.hset(key, categoryId+"",  tbContents);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }
}