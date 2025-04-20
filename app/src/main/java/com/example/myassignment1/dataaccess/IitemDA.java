package com.example.myassignment1.dataaccess;

import java.util.List;

public interface IitemDA {
    String[] getCats();
    List<Item> getItemsByCat(String cat);
}
