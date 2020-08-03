package io.dmcapps.dshopping.stock;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class StockEntryService {


    @Transactional(SUPPORTS)
    public List<StockEntry> findAllStockEntries() {
        return StockEntry.listAll();
    }

    @Transactional(SUPPORTS)
    public StockEntry findStockEntryById(Long id) {
        return StockEntry.findById(id);
    }
    
    @Transactional(SUPPORTS)
    public List<StockEntry> findStockEntryByStoreProduct(String storeId, Long productId) {
        return StockEntry.find("storeId = ?1 and productId = ?2", storeId, productId).list();   
    }

    public StockEntry persistStockEntry(@Valid StockEntry stockEntry) {
        StockEntry.persist(stockEntry);
        return stockEntry;
    }

    public StockEntry updateStockEntry(@Valid StockEntry stockEntry) {
        StockEntry entity = StockEntry.findById(stockEntry.id);
        entity.amount = stockEntry.amount;
        return entity;
    }

    public void deleteStockEntry(Long id) {
        StockEntry stockEntry = StockEntry.findById(id);
        stockEntry.delete();
    }

}