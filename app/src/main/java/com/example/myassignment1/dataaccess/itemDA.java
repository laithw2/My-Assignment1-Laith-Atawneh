package com.example.myassignment1.dataaccess;

import com.example.myassignment1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class itemDA implements IitemDA{
private List <Item> items = new ArrayList<>();
    public itemDA (){
        items.add(new Item("Hoodie - Bladi","Hoodie",133.99,5, R.drawable.bladi));
        items.add(new Item("Hoodie - Kuffiye","Hoodie",133.99,9, R.drawable.kuffiye));
        items.add(new Item("Hoodie - Palestinian Airlines","Hoodie",119.99,12, R.drawable.airlines));
        items.add(new Item("Hoodie - This sea is mine","Hoodie",133.99,15, R.drawable.sea));
        items.add(new Item("Hoodie - Old streets of Jerusalem","Hoodie",114.99,6, R.drawable.jerusalem));

        items.add(new Item("T-shirt - And Then?","T-shirt",78.99,12, R.drawable.tshirtandthen));
        items.add(new Item("T-shirt - Palestine Orange","T-shirt",68.99,10, R.drawable.jerusalem));
        items.add(new Item("T-shirt - Not in the Mood","T-shirt",75.99,13, R.drawable.tshirtnotinmood));

        items.add(new Item("Mobile Case - Where to Ramallah","Mobile Case",30.99,20, R.drawable.caseramallah));
        items.add(new Item("Mobile Case - Free, Free and Free","Mobile Case",30.99,20, R.drawable.casefree));
        items.add(new Item("Mobile case - Map of Palestine","Mobile Case",30.99,20, R.drawable.casemap));

        items.add(new Item("Tote Bag - Maze Palestine Map","Tote Bag",48.99,15, R.drawable.bagmap));
        items.add(new Item("Tote Bag - Watermelon","Tote Bag",48.99,10, R.drawable.bagwatermelon));

        items.add(new Item("Mug - Map of Palestine","Mug",19.99,10, R.drawable.mugmapofpalestine));
        items.add(new Item("Mug - Ka'ak Ka'ak","Mug",19.99,10, R.drawable.mugkak));
        items.add(new Item("Mug - Palestine","Mug",19.99,10, R.drawable.mugpalestine));
        items.add(new Item("Mug - Old is gold","Mug",19.99,10, R.drawable.mugoldisgold));










    }


    @Override
    public String[] getCats() {
        String[] cats = {"Hoodie", "T-shirt", "Mobile Case","Tote Bag","Mug"};
        return cats;
    }

    @Override
    public List<Item> getItemsByCat(String cat) {
        List<Item> result = new ArrayList<>();

        for(Item b: items){
            if(b.getCategory().equals(cat)){
                result.add(b);
            }
        }
        return result;

    }

    public List<Item> getItems() {
        return items;
    }
}
