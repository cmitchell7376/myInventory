package com.example.myInventory.models.data;

import com.example.myInventory.models.Inventory;
import com.example.myInventory.models.Item;
import com.example.myInventory.models.Supplier;
import javassist.bytecode.stackmap.BasicBlock;


import java.util.ArrayList;

public class SupplierData {

    private static ArrayList<Supplier> suppliers = new ArrayList<>();

    public static ArrayList<Supplier> getAll(){
        return suppliers;
    }

    public static void add(Supplier supplier){
        suppliers.add(supplier);
    }

    public static void remove(int id){
        Supplier newSupplier = getById(id);
        suppliers.remove(newSupplier);
    }

    //getById
    public static Supplier getById(int id){
        Supplier requestedStore = null;
        for (Supplier supplier : suppliers) {
            if(supplier.getSupplierId() == id){
                requestedStore = supplier;
            }
        }
        return requestedStore;
    }
}
