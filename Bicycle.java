package com.company;

public class Bicycle extends Vehicle {
    public Bicycle() {
        this.setAttributes();
    }
    void setAttributes() {
        this.setTip("Moped");
        this.setGauge(1);
        this.setCost(1);
    }
}