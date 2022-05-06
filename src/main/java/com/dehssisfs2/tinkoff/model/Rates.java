package com.dehssisfs2.tinkoff.model;

public class Rates {
    private String category;
    private double buy;
    private double sell;
    private FromCurrency fromCurrency;
    private ToCurrency toCurrency;

    public Rates() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public FromCurrency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(FromCurrency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public ToCurrency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(ToCurrency toCurrency) {
        this.toCurrency = toCurrency;
    }
}