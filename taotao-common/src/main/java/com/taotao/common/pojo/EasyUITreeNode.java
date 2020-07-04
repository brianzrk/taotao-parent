package com.taotao.common.pojo;

import lombok.Data;

import java.io.Serializable;
/**
 * tree 控件的节点的pojo
 * @author zhangrk
 * @version 1.0
 */
@Data
public class EasyUITreeNode implements Serializable {
	private Long id;
	private String text;
	private String state;
	
}
