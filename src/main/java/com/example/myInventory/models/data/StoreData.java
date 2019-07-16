package com.example.myInventory.models.data;

import com.example.myInventory.models.Store;

import java.util.ArrayList;

public class StoreData {

    static ArrayList<Store> stores = new ArrayList<>();

    //get all
    public static ArrayList<Store> getAll(){
        return stores;
    }

    //add
    public static void add(Store store){
        stores.add(store);
    }

    //remove
    public static void remove(int id){
        Store newStore = getById(id);
        stores.remove(newStore);
    }

    //getById
    public static Store getById(int id){
        Store requestedStore = null;
        for (Store store : stores) {
            if(store.getStoreId() == id){
                requestedStore = store;
            }
        }
        return requestedStore;
    }
}
