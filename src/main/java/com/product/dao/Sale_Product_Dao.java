package com.product.dao;

import com.product.entity.Product;
import com.product.entity.Sale;
import java.util.List;

public interface Sale_Product_Dao {

    public Sale createSale(Sale sale );
    public Sale getSale( int saleID );
    public void deleteSale(int saleID);
    public Sale updateSale(Sale sale);
    public List<Sale> getAllSales();
    public int getAllSalesAmount( int productID );

    public Product createProduct(String productName);
    public Product getProduct( int productID );
    public void deleteProduct(int productID);
    public Product updateProduct(Product product);
    public List<Product> getAllProducts();

}