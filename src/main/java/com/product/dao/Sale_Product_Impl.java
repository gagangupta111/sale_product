package com.product.dao;

import com.product.entity.Product;
import com.product.entity.Sale;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Qualifier("HibernateData")
public class Sale_Product_Impl implements Sale_Product_Dao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static void main(String[] args) {

        try {

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        } catch (Throwable ex) {

            System.err.println("Failed to create sessionFactory object." + ex);

        }

        Sale_Product_Impl SP = new Sale_Product_Impl();
        Product product = SP.createProduct("New_Product_888");
        Sale sale = new Sale(new Date(), 500, product);
        sale = SP.createSale(sale);

        Product product1 = SP.createProduct("New_Product_999");
        Sale sale1 = new Sale(new Date(), 500, product1);
        sale1 = SP.createSale(sale1);

        Product product2 = new Product(product.getProductID(),"New_Product_1000");
        product2 = SP.updateProduct(product2);

        System.out.println( SP.getAllSalesAmount(product.getProductID()) );

    }

    public Product getProduct( int productID ) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Product product = null;

        try {

            tx = session.beginTransaction();
            product =  (Product) session.get(Product.class, productID);
            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

        return product;

    }

    public Product createProduct(String productName) {

        Session session =sessionFactory.openSession();
        Transaction tx = null;
        Product product = null;
        Integer productID;

        try {

            tx = session.beginTransaction();
            product = new Product(productName);
            productID = (Integer) session.save(product);
            product.setProductID(productID.intValue());
            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

        return product;

    }

    public Product updateProduct(Product product) {

        Session session =sessionFactory.openSession();
        Transaction tx = null;
        Product fetchedProduct = null;

        try {

            tx = session.beginTransaction();
            fetchedProduct = (Product) session.load(Product.class, product.getProductID());
            fetchedProduct.setProductName(product.getProductName());
            session.update(fetchedProduct);
            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

        return fetchedProduct;

    }

    public void deleteProduct( int productID ) {

        Session session =sessionFactory.openSession();
        Transaction tx = null;
        Product product = null;

        try {

            tx = session.beginTransaction();
            product = (Product) session.load(Product.class, productID);
            session.delete(product);
            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

    }

    public List<Product> getAllProducts( ){

        Session session =sessionFactory.openSession();
        Transaction tx = null;
        List<Product> products = null;
        Product product;

        try {

            tx = session.beginTransaction();
            products = session.createQuery("from Product").list();
            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

        return products;

    }

    public Sale createSale( Sale sale){

        Session session =sessionFactory.openSession();
        Transaction tx = null;
        Integer saleID;

        try {

            tx = session.beginTransaction();
            saleID = (Integer) session.save(sale);
            sale.setSaleID(saleID.intValue());
            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

        return sale;

    }

    public Sale updateSale( Sale sale ){

        Session session =sessionFactory.openSession();
        Transaction tx = null;
        Sale fetchedSale = null;

        try {

            tx = session.beginTransaction();
            fetchedSale = (Sale) session.load(Sale.class, sale.getSaleID());

            fetchedSale.setSaleDate(sale.getSaleDate());
            fetchedSale.setSaleAmount(sale.getSaleAmount());
            fetchedSale.setProduct(sale.getProduct());

            session.update(fetchedSale);
            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

        return fetchedSale;

    }

    public void deleteSale( int saleID ){

        Session session =sessionFactory.openSession();
        Transaction tx = null;
        Sale sale = null;

        try {

            tx = session.beginTransaction();
            sale = (Sale) session.load(Sale.class, saleID);

            session.delete(sale);
            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

    }

    public Sale getSale( int saleID ){

        Session session =sessionFactory.openSession();
        Transaction tx = null;
        Sale sale = null;

        try {

            tx = session.beginTransaction();
            sale = (Sale) session.load(Sale.class, saleID);
            Hibernate.initialize(sale.getProduct());
            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

        return sale;

    }

    public List<Sale> getAllSales( ){

        Session session =sessionFactory.openSession();
        Transaction tx = null;
        List<Sale> sales = null;

        try {

            tx = session.beginTransaction();
            sales = session.createQuery("from Sale").list();

            for (Sale sale : sales){
                Hibernate.initialize(sale.getProduct());
            }

            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

        return sales;

    }

    public int getAllSalesAmount( int productID ){

        Session session =sessionFactory.openSession();
        Transaction tx = null;
        List<Sale> sales = null;
        Product product;
        int salesAmount = 0;

        try {

            tx = session.beginTransaction();
            product = (Product) session.load(Product.class, productID);

            sales = session.createCriteria(Sale.class)
                    .add( Restrictions.like("product", product  ))
                    .list();


            for (Sale sale : sales){
                salesAmount = salesAmount + sale.getSaleAmount();
            }

            tx.commit();

        } catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {

            session.close();

        }

        return salesAmount;

    }


}
