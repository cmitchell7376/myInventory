package com.example.myInventory.models.data;

import com.example.myInventory.models.Item;

import java.util.ArrayList;

public class ItemData {

    public static Item getItem(int id, int storeId){
        ArrayList<Item> items = StoreData.getById(storeId).getInventory().getItems();
        Item newItem = new Item();
        for (Item item: items) {
            if(item.getItemId() == id){
                newItem = item;
            }
        }
        return newItem;
    }
}
