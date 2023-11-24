package com.example.wanapp.info;

import com.example.wanapp.base.BaseItem;

/**
 * @Description 首页中循环的新闻标题类
 * @Author xmhaz
 * @Time 2023/11/23 9:16
 */
public class ArticleItem extends BaseItem {
    private String context;   //内容文本
    private String imgUrl;     //图片路径
    private ItemLabel label;    //动态的似乎


    //根据文本创建标签
    public ItemLabel addLabel(String text)
    {
        ItemLabel label = new ItemLabel(text);
        return label;
    }
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public ItemLabel getLabel() {
        return label;
    }

    public void setLabel(ItemLabel label) {
        this.label = label;
    }
}
