package com.example.myInventory.models.data;

import com.example.myInventory.models.Inventory;
import com.example.myInventory.models.Item;
import com.example.myInventory.models.Supplier;
import javassist.bytecode.stackmap.BasicBlock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SupplierData {

    private static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static boolean isDataLoaded = false;

    public static void loadSuppliers(){

        if(isDataLoaded){
            return;
        }

        // Meat supplier
        Supplier cargill = new Supplier("Cargill","Meat","5042 IL-162","Pontoon Beanch",
                "Illinos","62040");
        Inventory inventory = new Inventory("Cargill Inventory");
        cargill.setInventory(inventory);

        try{

            File file = new File("C:\\Users\\cmitc\\IdeaProjects\\myInventory\\src\\main\\resources\\static" +
                    "\\meat_data.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] lineArray = line.split(",");
                String name = lineArray[0];
                Double price = Double.parseDouble(lineArray[1]);
                Item item = new Item(name,price);
                cargill.getInventory().addItem(item);
            }
        }
        catch(IOException e){
            System.out.println("file not found");
            e.printStackTrace();
        }

        suppliers.add(cargill);
        isDataLoaded = true;
    }

    public static ArrayList<Supplier> getAll(){
        loadSuppliers();
        return suppliers;
    }
}
