package com.taotao.common.pojo;

import lombok.Data;

import java.io.Serializable;
/**
 * 搜索的商品数据POJO
 * @title SearchItem.java
 * <p>description</p>
 * <p>company: www.itheima.com</p>
 * @author ljh 
 * @version 1.0
 */
@Data
public class SearchItem implements Serializable {
	private Long id;//商品的id 
	private String title;//商品标题
	private String sell_point;//商品卖点
	private Long price;//价格
	private String image;//商品图片的路径
	private String category_name;//商品分类名称
	private String item_desc;//商品的描述

	public String[] getImages(){
		if(this.getImage()!=null){
			String[] split = this.getImage().split(",");
			return split;
		}
		return null;
	}

}
