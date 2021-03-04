package com.company;

public class Accident extends TrafficJam {
    private int Cost;
    public Accident(int cost, int start, int end){
        this.setStart(start);
        this.setEnd(end);
        this.Cost = cost;
    }
    void setCost(int Cost) {
        this.Cost = Cost;
    }
    int getCost() {
        return Cost;
    }
}
