package com.dehssisfs2.tinkoff.model;

import java.util.ArrayList;


public class Payload {
    private LastUpdate lastUpdate;
    private ArrayList<Rates> rates;

    public Payload() {
    }

    public LastUpdate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LastUpdate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public ArrayList<Rates> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rates> rates) {
        this.rates = rates;
    }
}