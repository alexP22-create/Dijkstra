package com.company;

public class Street {
    private int cost;
    private int limitaGabarit;
    private int start;
    private int end;
    public Street(int cost, int limita, int n1, int n2) {
        this.cost = cost;
        this.limitaGabarit = limita;
        this.start = n1;
        this.end = n2;
    }
    void setCost(int cost) {
        this.cost = cost;
    }
    int getCost() {
        return cost;
    }
    void setLimitaGabarit(int cost) {
        this.limitaGabarit = cost;
    }
    int getLimitaGabarit() {
        return limitaGabarit;
    }
    void setStart(int cost) {
        this.start = cost;
    }
    int getStart() {
        return start;
    }
    void setEnd(int cost) {
        this.end = cost;
    }
    int getEnd() {
        return end;
    }
}
