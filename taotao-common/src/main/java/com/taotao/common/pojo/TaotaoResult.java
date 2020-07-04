package com.taotao.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 统一响应结果
 */
@Data
public class TaotaoResult implements Serializable{
	
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    //跳转路径
    private String action;

    public static TaotaoResult success() {
        return TaotaoResult.success(200, "OK", null);
    }

    public static TaotaoResult success(String msg) {
        return TaotaoResult.success(200, msg, null);
    }

    public static TaotaoResult success(Object data) {
        return TaotaoResult.success(200, "OK", data);
    }


    public static TaotaoResult success(Integer status, String msg, Object data) {
        TaotaoResult result = new TaotaoResult();
        result.status = status;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static TaotaoResult fail(String msg) {
        TaotaoResult result = new TaotaoResult();
        result.status = -1;
        result.data = null;
        result.msg = msg;
        return result;
    }

    public TaotaoResult action(String action) {
        this.action = action;
        return this;
    }

}
