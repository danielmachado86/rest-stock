package io.dmcapps.dshopping.stock.client;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;


@Schema(description="The hero fighting against the villain")
public class Product {

    public String id;
    public String name;
    public ProductCategory category;
    public Brand brand;
    public String picture;
    public String barcode;
    public HashMap<String, Object> description;
    public ArrayList<String> tags;

}