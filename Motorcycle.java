package com.company;

public class Motorcycle extends Vehicle {
    public Motorcycle() {
        this.setAttributes();
    }
    void setAttributes() {
        this.setTip("Moped");
        this.setGauge(1);
        this.setCost(2);
    }
}
