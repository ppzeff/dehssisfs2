package com.dehssisfs2.LOTO.models;

import javax.persistence.*;

@Entity
@Table(name = "waste_workers_model")
public class WasteWorkersModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String FIO;

    public WasteWorkersModel() {
    }

    public WasteWorkersModel(String FIO) {
        this.FIO = FIO;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "WasteWorkersModel{" +
                "id=" + id +
                ", FIO='" + FIO + '\'' +
                '}';
    }
}
