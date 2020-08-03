package io.dmcapps.dshopping.stock;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@IdClass(StockId.class)
@Entity
public class Stock extends PanacheEntityBase {
    
    @Id
    public String storeId;
    @Id
    public String productId;
    public double price;
}

// Helper class for composite id entities
class StockId implements Serializable{

	private static final long serialVersionUID = 1L;
	String storeId;
    String productId;
}