package com.taotao.common.pojo;

import lombok.Data;

import java.io.Serializable;
/**
 * datagrid 展示数据的POJO 包括商品的POJO
 * @author zhangrk
 * @version 1.0
 */
import java.util.List;

@Data
public class EasyUIDataGridResult<T> implements Serializable {
	
	private Integer total;
	
	private List<T> rows;
	
}
