package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
import org.springframework.stereotype.Component;

@Component
@Service(timeout = 10000, interfaceClass = ItemCatService.class)
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper catMapper;
	@Override
	public List<EasyUITreeNode> getItemCatListByParentId(Long parentId) {
		//1.注入mapper
		//2.创建example
		TbItemCatExample example = new TbItemCatExample();
		//3.设置查询的条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);//select *ｆｒｏｍ　ｔｂｉｔｅｍｃａｔ　ｗｈｅｒｅ　ｐａｒｅｎｔＩｄ＝１
		//4.执行查询  list<ibitemCat>
		List<TbItemCat> list = catMapper.selectByExample(example);
		//5.转成需要的数据类型List<EasyUITreeNode>
		List<EasyUITreeNode> nodes = new ArrayList<>();
		for (TbItemCat cat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");//"open",closed
			nodes.add(node);
		}
		//6.返回
		return nodes;
	}

}
