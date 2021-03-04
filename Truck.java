package com.company;

public class Truck extends Vehicle {
    public Truck() {
        this.setAttributes();
    }
    void setAttributes() {
        this.setTip("Autoutilitar");
        this.setGauge(3);
        this.setCost(6);
    }
}
