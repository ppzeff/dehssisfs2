package com.dehssisfs2.tinkoff.model;

import javax.persistence.*;

@Entity
@Table(name = "rates_USD")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "last_update")
    private Long LastUpdate;
    @Column(name = "code")
    private int code;

    private double rateRUB;
    private double rateVal;

    public Model() {
    }

    public Model(int code, double rateRUB, double rateVal) {
        this.code = code;
        this.rateRUB = rateRUB;
        this.rateVal = rateVal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        LastUpdate = lastUpdate;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getRateRUB() {
        return rateRUB;
    }

    public void setRateRUB(double rateRUB) {
        this.rateRUB = rateRUB;
    }

    public double getRateVal() {
        return rateVal;
    }

    public void setRateVal(double rateVal) {
        this.rateVal = rateVal;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", LastUpdate=" + LastUpdate +
                ", code=" + code +
                ", rateRUB=" + rateRUB +
                ", rateVal=" + rateVal +
                '}';
    }

    public String toTeleString() {
        String codeToStr = "";
        switch (code) {
            case 840:
                codeToStr = "<b>$ USD (доллар)</b>";
                break;
            case 978:
                codeToStr = "<b>€ EUR (евро)</b>";
                break;
            case 826:
                codeToStr = "<b>£ GBP (фунт)</b>";
                break;
        }

        return codeToStr + "\n" +
                "Покупка: " + rateRUB + " руб.\n" +
                "Продажа: " + rateVal + " руб.\n";
    }
}
