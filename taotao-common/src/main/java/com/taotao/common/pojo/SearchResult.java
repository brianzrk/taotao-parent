package com.taotao.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SearchResult implements Serializable {
	private List<SearchItem> itemList;// 搜索结果列表
	private long recordCount;// 总记录数
	private long pageCount;// 总页数
}
