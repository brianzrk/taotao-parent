package com.taotao.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author zhangrk
 * @date 2020/6/13
 */
@Data
@Document(indexName = "item", type = "item", createIndex = true)
public class SearchItemDocument implements Serializable{

    @Id
    private Long id;//商品的id

    //ik分词器                     是否保存到硬盘
    @Field(type = FieldType.Text, store = true, searchAnalyzer="ik_smart", analyzer = "ik_max_word")
    private String title;//商品标题

    @Field(type = FieldType.Text, store = true, searchAnalyzer="ik_smart", analyzer = "ik_max_word")
    private String sell_point;//商品卖点

    //可加可不加:因为不是搜索的内容
    @Field(type = FieldType.Long)
    private Long price;//价格
    private String image;//商品图片的路径

    @Field(type = FieldType.Text, store = true, searchAnalyzer="ik_smart", analyzer = "ik_max_word")
    private String category_name;//商品分类名称

    @Field(type = FieldType.Text, store = true, searchAnalyzer="ik_smart", analyzer = "ik_max_word")
    private String item_desc;//商品的描述

    public String[] getImages() {
        if (this.getImage() != null) {
            String[] split = this.getImage().split(",");
            return split;
        }
        return null;
    }

}