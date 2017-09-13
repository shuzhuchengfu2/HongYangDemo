package com.hongyangdemo.others;

import com.hongyangdemo.domain.Item;

import java.util.List;

/**
 * author： xiongdejin
 * date: 2017/9/13
 * describe:
 */

public class Event {
    /**
     * 列表加载事件
     */
    public static class ItemListEvent {
        private List<Item> items;

        public ItemListEvent(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }
    }
}
