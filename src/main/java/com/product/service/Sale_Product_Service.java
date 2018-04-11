package com.product.service;

import com.product.dao.Sale_Product_Dao;
import com.product.entity.Product;
import com.product.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Sale_Product_Service {

    @Autowired
    @Qualifier("HibernateData")
    private Sale_Product_Dao sale_product_dao;

    public Sale getSale( int saleID ){

        return sale_product_dao.getSale(saleID);

    }

    public Product getProduct( int productID ){

        return sale_product_dao.getProduct(productID);

    }

    public Sale createSale(Sale sale ){

        return sale_product_dao.createSale(sale);

    }

    public void deleteSale(int saleID){

        sale_product_dao.deleteSale(saleID);

    }

    public Sale updateSale(Sale sale){

        return sale_product_dao.updateSale(sale);

    }

    public List<Sale> getAllSales(){

        return sale_product_dao.getAllSales();

    }
    public int getAllSalesAmount( int productID ){

        return sale_product_dao.getAllSalesAmount(productID);

    }

    public Product createProduct(String productName){

        return sale_product_dao.createProduct(productName);

    }

    public void deleteProduct(int productID){

        sale_product_dao.deleteProduct(productID);

    }

    public Product updateProduct(Product product){

        return sale_product_dao.updateProduct(product);

    }

    public List<Product> getAllProducts(){

        return sale_product_dao.getAllProducts();

    }

}
