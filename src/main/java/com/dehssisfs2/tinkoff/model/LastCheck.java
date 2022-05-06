package com.dehssisfs2.tinkoff.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LastCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int code;
    private long lastID;

    public LastCheck() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getLastID() {
        return lastID;
    }

    public void setLastID(long lastID) {
        this.lastID = lastID;
    }
}
