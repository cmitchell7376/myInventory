package com.example.myInventory.models.data;

import com.example.myInventory.models.Item;
import com.example.myInventory.models.Store;
import java.util.List;

public class ItemData {


    private static StoreDao storeDao;

    public static Item getItem(int id, Store store){
        List<Item> items = store.getInventory().getItems();
        Item newItem = new Item();
        for (Item item: items) {
            if(item.getId() == id){
                newItem = item;
            }
        }
        return newItem;
    }
}
