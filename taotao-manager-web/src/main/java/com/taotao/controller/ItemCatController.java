package com.taotao.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.service.ItemCatService;

@Controller
public class ItemCatController {

	@Reference
	private ItemCatService catService;
	//url:'/item/cat/list',
	//参数：id
	//返回值：json
	//method:get post
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		//1.引入服务
		//2.注入服务
		//3.调用方法
		List<EasyUITreeNode> list = catService.getItemCatListByParentId(parentId);
		return list;
	}
}
