package io.dmcapps.dshopping.stock.client;

import java.time.Instant;

import io.dmcapps.dshopping.stock.client.address.Address;

public class Store {
    
    public String id;
    public String name;
    public String email;
    public String mobile;
    public Address address;
    public Instant dateCreated;
    public Instant dateUpdated;
}