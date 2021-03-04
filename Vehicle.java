package com.company;

public abstract class Vehicle {
    private String Tip;
    private int gauge;
    private int Cost;
    void setTip(String a) {
        this.Tip = a;
    }
    void setGauge(int nr){
        this.gauge = nr;
    }
    void setCost(int cost) {
        this.Cost = cost;
    }
    int getGauge() {
        return gauge;
    }
    int getCost() {
        return Cost;
    }
    String getTip() {
        return Tip;
    }

    abstract void setAttributes();
}
