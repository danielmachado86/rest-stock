package io.dmcapps.dshopping.stock;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class StockService {


    @Transactional(SUPPORTS)
    public List<Stock> findAllStocks() {
        return Stock.listAll();
    }

    @Transactional(SUPPORTS)
    public Stock findStockById(String storeId, String productId) {
        return Stock.find("storeId = ?1 and productId = ?2", storeId, productId).firstResult();

    }

    public Stock persistStock(@Valid Stock stock) {
        Stock.persist(stock);
        return stock;
    }

    public Stock updateStock(@Valid Stock stock) {
        Stock entity = Stock.findById(stock.storeId);
        entity.price = stock.price;
        return entity;
    }

    public void deleteStock(Long id) {
        Stock stock = Stock.findById(id);
        stock.delete();
    }
}