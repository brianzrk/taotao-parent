package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.stereotype.Component;

@Component
@Service(timeout = 10000, interfaceClass = ItemService.class)
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper mapper;
    @Autowired
    private TbItemDescMapper descMapper;

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //1.设置分页的信息 使用pagehelper
        if (page == null) page = 1;
        if (rows == null) rows = 30;
        PageHelper.startPage(page, rows);
        //2.注入mapper
        //3.创建example 对象 不需要设置查询条件
        TbItemExample example = new TbItemExample();
        //4.根据mapper调用查询所有数据的方法
        PageHelper.startPage(page, rows);
        List<TbItem> list = mapper.selectByExample(example);
        //5.获取分页的信息
        PageInfo<TbItem> info = new PageInfo<>(list);
        //6.封装到EasyUIDataGridResult
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal((int) info.getTotal());
        result.setRows(info.getList());
        //7.返回
        return result;
    }

    @Override
    public TaotaoResult saveItem(TbItem item, String desc) {
        //生成商品的id
        long itemId = IDUtils.genItemId();
        //1.补全item 的其他属性
        item.setId(itemId);
        item.setCreated(new Date());
        //1-正常，2-下架，3-删除',
        item.setStatus((byte) 1);
        item.setUpdated(item.getCreated());
        //2.插入到item表 商品的基本信息表
        mapper.insertSelective(item);
        //3.补全商品描述中的属性
        TbItemDesc desc2 = new TbItemDesc();
        desc2.setItemDesc(desc);
        desc2.setItemId(itemId);
        desc2.setCreated(item.getCreated());
        desc2.setUpdated(item.getCreated());
        //4.插入商品描述数据
        //注入tbitemdesc的mapper
        descMapper.insertSelective(desc2);
        //5.返回taotaoresult
        return TaotaoResult.success();
    }

}
