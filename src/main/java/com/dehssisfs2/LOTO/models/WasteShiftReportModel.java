package com.dehssisfs2.LOTO.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "dev_waste_shift_report")
public class WasteShiftReportModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "reportDate")
    private Timestamp reportDate;
    @Column(name = "firstFIO")
    private String firstFIO;
    @Column(name = "secondFIO")
    private String secondFIO;
    @Column(name = "comment")
    private String comment;
    @Column(name = "firstFoto")
    private String firstFoto;
    @Column(name = "secondFoto")
    private String secondFoto;
    @Column(name = "thirdFoto")
    private String thirdFoto;
    @Column(name = "fourthFoto")
    private String fourthFoto;
    @Column(name = "numberPallets")
    private int numberPallets;
    @Column(name = "numberBoxes")
    private int numberBoxes;
    @Column(name = "numberWagons")
    private int numberWagons;
    private boolean shiftDay;

    public WasteShiftReportModel() {
    }

    public WasteShiftReportModel(String firstFIO, String secondFIO, int numberWagons, int numberBoxes, int numberPallets, String comment, String firstFoto, String secondFoto, String thirdFoto, String fourthFoto, boolean shiftDay) {
        this.firstFIO = firstFIO;
        this.secondFIO = secondFIO;
        this.numberWagons = numberWagons;
        this.numberBoxes = numberBoxes;
        this.numberPallets = numberPallets;
        this.comment = comment;
        this.firstFoto = firstFoto;
        this.secondFoto = secondFoto;
        this.thirdFoto = thirdFoto;
        this.fourthFoto = fourthFoto;
        this.shiftDay = shiftDay;
        this.reportDate = new Timestamp(new Date().getTime());
    }

    @Override
    public String toString() {
        return "WasteShiftReportModel{" +
                "id=" + id +
                ", reportDate=" + reportDate +
                ", firstFIO='" + firstFIO + '\'' +
                ", secondFIO='" + secondFIO + '\'' +
                ", comment='" + comment + '\'' +
                ", firstFoto='" + firstFoto + '\'' +
                ", secondFoto='" + secondFoto + '\'' +
                ", thirdFoto='" + thirdFoto + '\'' +
                ", fourthFoto='" + fourthFoto + '\'' +
                ", numberPallets=" + numberPallets +
                ", numberBoxes=" + numberBoxes +
                ", numberWagons=" + numberWagons +
                ", shiftDay=" + shiftDay +
                '}';
    }

    public long getId() {
        return id;
    }

    public Timestamp getReportDate() {
        return reportDate;
    }

    public String getFirstFIO() {
        return firstFIO;
    }

    public void setFirstFIO(String firstFIO) {
        this.firstFIO = firstFIO;
    }

    public String getSecondFIO() {
        return secondFIO;
    }

    public void setSecondFIO(String secondFIO) {
        this.secondFIO = secondFIO;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFirstFoto() {
        return firstFoto;
    }

    public void setFirstFoto(String firstFoto) {
        this.firstFoto = firstFoto;
    }

    public String getSecondFoto() {
        return secondFoto;
    }

    public void setSecondFoto(String secondFoto) {
        this.secondFoto = secondFoto;
    }

    public String getThirdFoto() {
        return thirdFoto;
    }

    public void setThirdFoto(String thirdFoto) {
        this.thirdFoto = thirdFoto;
    }

    public String getFourthFoto() {
        return fourthFoto;
    }

    public void setFourthFoto(String fourthFoto) {
        this.fourthFoto = fourthFoto;
    }

    public boolean isShiftDay() {
        return shiftDay;
    }

    public void setShiftDay(boolean shiftDay) {
        this.shiftDay = shiftDay;
    }

    public int getNumberPallets() {
        return numberPallets;
    }

    public void setNumberPallets(int numberPallets) {
        this.numberPallets = numberPallets;
    }

    public int getNumberBoxes() {
        return numberBoxes;
    }

    public void setNumberBoxes(int numberBoxes) {
        this.numberBoxes = numberBoxes;
    }

    public int getNumberWagons() {
        return numberWagons;
    }

    public void setNumberWagons(int numberWagons) {
        this.numberWagons = numberWagons;
    }
}