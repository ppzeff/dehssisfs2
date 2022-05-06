package com.dehssisfs2.tinkoff.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "telegram_user")
public class TelegramUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long UserId;
    private String firstName;
    private String lastName;
    private String userName;
    private boolean isBot;
    private Date startDate;
    private double alertSell840;
    private double alertSell826;
    private double alertSell978;

    private double alertBuy840;
    private double alertBuy826;
    private double alertBuy978;

    public TelegramUser() {
    }

    public TelegramUser(long userId, String firstName, String lastName, String userName, boolean isBot, Date startDate) {
        UserId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.isBot = isBot;
        this.startDate = startDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getAlertSell840() {
        return alertSell840;
    }

    public void setAlertSell840(double alertSell840) {
        this.alertSell840 = alertSell840;
    }

    public double getAlertSell826() {
        return alertSell826;
    }

    public void setAlertSell826(double alertSell826) {
        this.alertSell826 = alertSell826;
    }

    public double getAlertSell978() {
        return alertSell978;
    }

    public void setAlertSell978(double alertSell978) {
        this.alertSell978 = alertSell978;
    }

    public double getAlertBuy840() {
        return alertBuy840;
    }

    public void setAlertBuy840(double alertBuy840) {
        this.alertBuy840 = alertBuy840;
    }

    public double getAlertBuy826() {
        return alertBuy826;
    }

    public void setAlertBuy826(double alertBuy826) {
        this.alertBuy826 = alertBuy826;
    }

    public double getAlertBuy978() {
        return alertBuy978;
    }

    public void setAlertBuy978(double alertBuy978) {
        this.alertBuy978 = alertBuy978;
    }

    @Override
    public String toString() {
        return "TelegramUser{" +
                "id=" + id +
                ", UserId=" + UserId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", isBot=" + isBot +
                ", startDate=" + startDate +
                ", alertSell840=" + alertSell840 +
                ", alertSell826=" + alertSell826 +
                ", alertSell978=" + alertSell978 +
                ", alertBuy840=" + alertBuy840 +
                ", alertBuy826=" + alertBuy826 +
                ", alertBuy978=" + alertBuy978 +
                '}';
    }
}
