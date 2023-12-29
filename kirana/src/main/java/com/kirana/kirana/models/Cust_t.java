package com.kirana.kirana.models;

import java.text.DecimalFormat;

public class Cust_t {
    private String custId;
    private String total_profit_in_inr;
    private String total_profit_in_usd;
    private String total_debit_in_inr;
    private String total_debit_in_usd;
    private String total_credit_in_inr;
    private String total_credit_in_usd;

    public Cust_t(String custId, double total_profit_in_inr, double total_profit_in_usd, double total_debit_in_inr,
            double total_debit_in_usd, double total_credit_in_inr, double total_credit_in_usd) {
        DecimalFormat df = new DecimalFormat("#.00");
        total_credit_in_usd = Double.valueOf(df.format(total_credit_in_usd));
        total_profit_in_usd = Double.valueOf(df.format(total_profit_in_usd));
        total_debit_in_usd = Double.valueOf(df.format(total_debit_in_usd));
        this.custId = custId;
        this.total_credit_in_inr = "Rs." + total_credit_in_inr;
        this.total_credit_in_usd = total_credit_in_usd + "$";
        this.total_debit_in_inr = "Rs." + total_debit_in_inr;
        this.total_debit_in_usd = total_debit_in_usd + "$";
        this.total_profit_in_inr = "Rs." + total_profit_in_inr;
        this.total_profit_in_usd = total_profit_in_usd + "$";
    }

    String getcustId() {
        return this.custId;
    }

    String gettotal_profit_in_inr() {
        return this.total_profit_in_inr;
    }

    String gettotal_profit_in_usd() {
        return this.total_profit_in_usd;
    }

    String gettotal_debit_in_inr() {
        return this.total_debit_in_inr;
    }

    String gettotal_debit_in_usd() {
        return this.total_debit_in_usd;
    }

    String gettotal_credit_in_inr() {
        return this.total_credit_in_inr;
    }

    String gettotal_credit_in_usd() {
        return this.total_credit_in_usd;
    }

    public void display() {
        System.out.println(custId + " " + total_credit_in_inr + " " + total_credit_in_usd);
    }
}
