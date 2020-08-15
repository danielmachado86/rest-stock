package io.dmcapps.dshopping.stock.client.address;

import java.util.ArrayList;

public class Location {

    public String type = "Point";
    public ArrayList<Double> coordinates;

    public Location(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public Location() {
        
    }

}
