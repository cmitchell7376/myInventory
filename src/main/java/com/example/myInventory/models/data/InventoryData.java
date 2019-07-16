package com.example.myInventory.models.data;

import com.example.myInventory.models.Item;
import com.example.myInventory.models.Store;

import java.util.ArrayList;

public class InventoryData {

    //check item by name
    public static Item checkByName(int storeId, int itemId){

        Store store = StoreData.getById(storeId);
        ArrayList<Item> items = store.getInventory().getItems();

        for (Item item: items) {
            if (item.getItemId() == itemId){
                return item;
            }
        }
        return null;
    }
}
