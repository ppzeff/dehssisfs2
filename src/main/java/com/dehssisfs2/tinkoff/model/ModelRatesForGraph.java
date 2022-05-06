package com.dehssisfs2.tinkoff.model;

import java.util.Date;

public class ModelRatesForGraph implements Comparable<ModelRatesForGraph>{
private Date date;
private Double rateBuy;
private Double rateSell;



    public ModelRatesForGraph(Date date, Double rateBuy, Double rateSell) {
        this.date = date;
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(Double rateBuy) {
        this.rateBuy = rateBuy;
    }

    public Double getRateSell() {
        return rateSell;
    }

    public void setRateSell(Double rateSell) {
        this.rateSell = rateSell;
    }

    @Override
    public int compareTo(ModelRatesForGraph o) {
        return this.getDate().compareTo(o.getDate());
    }
}
