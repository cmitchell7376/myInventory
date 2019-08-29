package com.example.myInventory.models.data;

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
}
