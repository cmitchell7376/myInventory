package com.example.myInventory.models.data;

import com.example.myInventory.models.EquipmentStore;
import com.example.myInventory.models.Item;
import com.example.myInventory.models.Store;
import com.example.myInventory.models.User;
import com.example.myInventory.models.data.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchData {

    public static UserRepository userRepository;

    public static List<Store> storeSearch(User user, String searchRequest){

        List<Store> storeFound = new ArrayList<>();

        String [] storeNameSplit;
        List<Store> stores = user.getStores();

        for (Store store: stores){
            if(store.getName().equalsIgnoreCase(searchRequest)){
                storeFound.add(store);
            }else {
                storeNameSplit = store.getName().split(" ");

                for (String word : storeNameSplit) {
                    if (word.equalsIgnoreCase(searchRequest)) {
                        storeFound.add(store);
                    }
                }
            }
        }
        return storeFound;
    }

    public static List<Item> inventorySearchName(Store store, String searchRequest){

        List<Item> itemFound = new ArrayList<>();

        String [] itemNameSplit;
        List<Item> items = store.getInventory().getItems();

        for (Item item: items) {
            if(item.getName().equalsIgnoreCase(searchRequest)){
                itemFound.add(item);
            }else {
                itemNameSplit = item.getName().split("");

                for (String word : itemNameSplit) {
                    if(word.equalsIgnoreCase(searchRequest)){
                        itemFound.add(item);
                    }
                }
            }
        }
        return itemFound;
    }

    public static List<Item> inventorySearchCode(Store store, String searchRequest){

        List<Item> itemFound = new ArrayList<>();

        List<Item> items = store.getInventory().getItems();

        for (Item item: items) {
            if(item.getBarCode().equalsIgnoreCase(searchRequest)){
                itemFound.add(item);
            }
        }
        return itemFound;
    }

    public static List<EquipmentStore> equipmentStoreSearch(User user, String searchRequest){

        List<EquipmentStore> storeFound = new ArrayList<>();

        String [] storeNameSplit;
        List<EquipmentStore> stores = user.getEquipmentStores();

        for (EquipmentStore store: stores){
            if(store.getName().equalsIgnoreCase(searchRequest)){
                storeFound.add(store);
            }else {
                storeNameSplit = store.getName().split(" ");

                for (String word : storeNameSplit) {
                    if (word.equalsIgnoreCase(searchRequest)) {
                        storeFound.add(store);
                    }
                }
            }
        }
        return storeFound;
    }
}
