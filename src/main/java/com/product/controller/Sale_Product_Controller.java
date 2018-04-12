package com.product.controller;

import com.product.entity.Product;
import com.product.entity.Sale;
import com.product.service.Sale_Product_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

// postman collection here.
// https://www.getpostman.com/collections/b92a0738d2285b9c171b

@RestController
@RequestMapping("/Sales")
public class Sale_Product_Controller {

    @Autowired
    private Sale_Product_Service sale_product_service;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Sale> getAllSales(){
        return sale_product_service.getAllSales();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Sale getSaleById(@PathVariable("id") int id){
        return sale_product_service.getSale(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Sale createSale(@RequestBody Sale sale){
        return sale_product_service.createSale(sale);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Sale updateSale(@RequestBody Sale sale){
        return sale_product_service.updateSale(sale);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSaleById(@PathVariable("id") int id){
        sale_product_service.deleteSale(id);
    }



    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public Collection<Product> getAllProducts(){
        return sale_product_service.getAllProducts();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") int id){
        return sale_product_service.getProduct(id);
    }

    @RequestMapping(value = "/products/create/{productName}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product createProduct(@PathVariable("productName") String productName){
        return sale_product_service.createProduct(productName);
    }

    @RequestMapping(value = "/products/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product product){
        return sale_product_service.updateProduct(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProductById(@PathVariable("id") int id){
        sale_product_service.deleteProduct(id);
    }

    @RequestMapping(value = "/products/sales_amount/{id}", method = RequestMethod.GET)
    public int getTotalSalesByProductID(@PathVariable("id") int id){
        return sale_product_service.getAllSalesAmount(id);
    }

}