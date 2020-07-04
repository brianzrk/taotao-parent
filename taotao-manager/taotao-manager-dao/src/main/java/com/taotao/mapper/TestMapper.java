package com.taotao.mapper;

import org.springframework.stereotype.Component;

/**
 * 测试接口 查询当前的时间
 * @author zhangrk
 * @version 1.0
 */

@Component
public interface TestMapper {
	public String queryNow();
}
