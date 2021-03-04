package com.company;

public class Traffic extends TrafficJam {
    private int timp;
    public Traffic(int timp, int start, int end){
        this.setStart(start);
        this.setEnd(end);
        this.timp = timp;
    }
    int getTimp() {
        return timp;
    }
}
