package com.company;

public class Jam extends TrafficJam {
    private int time;
    public Jam(int time, int start, int end) {
        this.setStart(start);
        this.setEnd(end);
        this.time = time;
    }
    int getTimp() {
        return time;
    }
}
