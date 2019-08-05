package com.example.myInventory.models.data;

import com.example.myInventory.models.Item;
import com.example.myInventory.models.Store;
import java.util.List;

public class InventoryData {


    private static StoreDao storeDao;

    //check item by name
    public static Item checkByName(Store store, int itemId){

        List<Item> items = store.getInventory().getItems();

        for (Item item: items) {
            if (item.getId() == itemId){
                return item;
            }
        }
        return null;
    }
}
