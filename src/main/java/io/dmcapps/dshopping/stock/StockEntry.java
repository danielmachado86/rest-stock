package io.dmcapps.dshopping.stock;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class StockEntry extends PanacheEntity {
    
    public String storeId;
    public String productId;
    public double amount;
}
