package com.pedro.interfaces.res;

import java.util.List;

/**
 * 返回给前端渲染表格的通用结果
 * @param <T>
 */
public class FormDataRes<T> {

    /**
     * 表单元素
     */
    List<T> items;

    /**
     * 表单计数
     */
    int total;

    public FormDataRes() {
    }

    public FormDataRes(List<T> items) {
        this.items = items;
        this.total = items.size();
    }

    public FormDataRes(List<T> items, int total) {
        this.items = items;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
