package com.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sale implements java.io.Serializable {

    private int saleID;
    private Date saleDate;
    private int saleAmount;
    private Product product;

    public Sale() {
    }

    public Sale(Date saleDate, int saleAmount, Product product) {
        this.saleDate = saleDate;
        this.saleAmount = saleAmount;
        this.product = product;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(int saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleID=" + saleID +
                ", saleDate=" + saleDate +
                ", saleAmount=" + saleAmount +
                ", product=" + product +
                '}';
    }
}
