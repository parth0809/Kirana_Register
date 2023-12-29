package com.kirana.kirana.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kirana_prod")
public class Kirana {
    private String prod_id;
    private String cust_id;
    private String date;
    private double price;
    private String currency;
    private int quantity;
    private String method;

    Kirana(String prod_id, String cust_id, double price, int quantity, String method, String currency) {
        this.prod_id = prod_id;
        this.cust_id = cust_id;
        this.price = price;
        this.quantity = quantity;
        this.method = method;
        this.currency = currency;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now).toString();
    }

    public String getProd_id() {
        return prod_id;
    }

    public String getCust_id() {
        return cust_id;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public String getMethod() {
        return method;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
