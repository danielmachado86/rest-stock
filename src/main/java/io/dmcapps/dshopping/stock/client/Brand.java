package io.dmcapps.dshopping.stock.client;

import java.util.ArrayList;

public class Brand {

    
    public String id;
    public String picture;
    public String description;
    public ArrayList<Product> topProducts = new ArrayList<Product>();
    public ArrayList<Product> newProducts = new ArrayList<Product>();

}