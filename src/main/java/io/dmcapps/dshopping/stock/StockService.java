package io.dmcapps.dshopping.stock;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import io.dmcapps.dshopping.stock.client.Product;
import io.dmcapps.dshopping.stock.client.ProductService;
import io.dmcapps.dshopping.stock.client.Store;
import io.dmcapps.dshopping.stock.client.StoreService;

import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class StockService {

    private static final Logger LOGGER = Logger.getLogger(StockService.class);

    @Inject
    @RestClient
    ProductService productService;

    @Inject
    @RestClient
    StoreService storeService;

    List<Stock> findStocks(String search, double lon, double lat) {
        List<Product> products = findProducts(search);
        List<Store> stores = findStores(lon, lat);
        String query = "";
        for (Product product:products){
            for (Store store:stores) {
                query += String.format("(storeId = '%s' AND productId = '%s')", store.id, product.id) + " OR ";
            }
        }
        if (query != null && query.length() > 0) {
            query = query.substring(0, query.length() - 4);
        }
        List<Stock> stocks = Stock.find(query).list();
        return stocks;
    }

    List<Product> findProducts(String search) {
        return productService.searchProduct(search);
    }

    List<Store> findStores(double lon, double lat) {
        return storeService.getNearByStores(lon, lat, 500);
    }


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