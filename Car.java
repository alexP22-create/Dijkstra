package com.company;

public class Car extends Vehicle {
    public Car() {
        this.setAttributes();
    }
    void setAttributes() {
        this.setTip("Autovehicul");
        this.setGauge(2);
        this.setCost(4);
    }
}
