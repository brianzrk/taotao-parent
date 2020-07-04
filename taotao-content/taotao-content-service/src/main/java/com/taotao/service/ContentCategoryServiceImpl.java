package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangrk
 * @date 2020/5/28
 */
@Component
@Service(timeout = 10000, interfaceClass = ContentCategoryService.class)
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper categoryMapper;

    @Override
    public List<TbContentCategory> getContentCategoryByParentId(Long parentId) {

        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);

        List<TbContentCategory> categories = categoryMapper.selectByExample(example);

        return categories;
    }

    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
        //1.注入mapper
        //2.创建example
        TbContentCategoryExample example = new TbContentCategoryExample();
        //3.设置条件
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);//select * from tbcontentcategory where parent_id=1
        //4.执行查询
        List<TbContentCategory> list = categoryMapper.selectByExample(example);
        //5.转成EasyUITreeNode 列表
        //
        List<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            node.setText(tbContentCategory.getName());//分类名称
            nodes.add(node);
        }
        //6.返回
        return nodes;
    }

    @Override
    public TaotaoResult createContentCategory(Long parentId, String name) {

        TbContentCategory category = new TbContentCategory();
        category.setParentId(parentId);
        category.setName(name);
        category.setCreated(new Date());
        category.setIsParent(false); //新增的节点是叶子节点
        category.setSortOrder(1);
        category.setStatus(1);
        category.setUpdated(category.getCreated());

        categoryMapper.insert(category);

        //如果parentId原先是叶子节点，则需要重新设置为父节点
        TbContentCategory parent = categoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            categoryMapper.updateByPrimaryKeySelective(parent);
        }

        return TaotaoResult.success(category);
    }
}