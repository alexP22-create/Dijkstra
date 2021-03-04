package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * We will use the PriorityQueue class to implement a priority queue.
 */
public class PriorityQueue {
    private Element varf;
    private Element coada;
    //private Element lastAded;
    private int start;
    private int end;
    private int lastCost;
    private Vehicle v;
    //we will use infinite to mark the value of an unreachable destination
    public static final int infinite = 100000;
    /**
     * This array is used to mark when a point has been passed.
     */
    private int[] sir;
    /**
     * This array is used to store the parents of every point.
     */
    //next array hold the parent of every point
    private int[] vec;
    //in urm queue se vor retine nodurile prin care s a trecut, care au fost verificate
    private Graph g;
    public PriorityQueue(){
        Element el = new Element();
        this.varf = el;
        this.coada = el;
    }
    /**
     * This method is using the Dijkstra alg to print the minimal cost used to go from start to end.
     */
    //next method is the one that uses the Dijkstra alg
    void drive(Vehicle v, int start, int end) throws IOException {
        int cost = this.stopDijkstra();
        if(this.varf.nod != this.getEnd())//in case the destination wasn't found yet
        while(cost == 0){
            //we will get the smallest cost from all the elements of the queue which is the queue's peak
            this.getMinRide();
            //now that we moved to another point the cost of the other elements will have to be recalculated
            this.recalculate();
            cost = this.stopDijkstra();
        }
        PrintWriter wr = new PrintWriter(new FileWriter("map.out", true));
        if(cost != infinite) {
            this.printTraseu(wr);
            wr.println(cost);
        }
        else {//if the cost is stil "infinite" it means the end is unreachable
            wr.println("P" + this.getStart() + " " + "P" + this.getEnd() + " null");
        }
        wr.close();
    }
    /**
     * This method adds an element in the priority queue.
     */
//next function adds an element in a priority queue based on it's value
    public void adauga(int x, int n){
        //initialli the peak's value is set to -1 so that we can know when it changed
        if (this.varf.val == -1) {
            this.varf.val = x;
            this.varf.nod = n;
        }
        else {
            PriorityQueue.Element el = new PriorityQueue.Element(x, n);
            Element p = this.varf;
            Element ant = null;
            if(this.coada.val <= x) {
                this.coada.urm = el;
                this.coada = el;
                return;
            }
            //we will search for the exact position in which to insert the new el using 2 pointers
            while(p != null){
                if(ant == null && x < p.val) {
                    //System.out.println("se ad in vf"+n+" "+x);
                    el.urm = this.varf;
                    this.varf = el;
                    return;
                }
                if(ant != null && x >= ant.val && x < p.val) {
                    ant.urm = el;
                    el.urm = p;
                    return;
                }
                ant = p;
                p = p.urm;
            }
        }
    }
    /**
     * This method tells us when to stop the Dijkstra alg.
     */
    //the alg will be stopped when the el with the cheapest cost will br the destination element
    public int stopDijkstra(){
        //the ending element is at the peaks in the stop case because the queue is in ascending order
        if(this.varf.nod == this.getEnd()) {
            return this.varf.val;
        }
        return 0;
    }
    /**
     * This method prints the road whose cost is the lowest.
     */
    public void printTraseu(PrintWriter wr){
        //the ending point element is left in the queue's peak
        ArrayList<Integer> values = new ArrayList<>();
        values.add(this.varf.nod);
        int i = this.varf.nod;

        if(this.vec[i] != -1) {//when the destination wasn't found without applying Dijkstra
            while (this.vec[i] != -1) {//the starting element has -1 as parent
                values.add(this.vec[i]);
                i = this.vec[i];
            }
            //the arraylist which holds the route is in inverse order
            for (int j = values.size() - 1; j >= 0; j--) {
                wr.print("P" + values.get(j) + " ");
            }
        }
        else {
            wr.print("P" + this.getStart() + " P" + this.getEnd() + " ");
        }

    }
    /**
     * This method selects the element with the lowest cost to reach.
     */
    public void getMinRide(){
        //lastNod will hold the last point which was selected
        int lastNod = 0;
        for(int i = 0; i < this.sir.length; i ++){
            if(this.sir[i] != -1)
                lastNod = this.sir[i];
        }
        Element el = this.varf;
        while(el != null){
            if(el.val != infinite && this.vec[el.nod] == -1){
                //we will set all the parents of the elements whose value changed from "infinite" with the last point reached
                this.vec[el.nod] = lastNod;
            }
            el = el.urm;
        }
        int min = this.varf.val;
        int nod = this.varf.nod;
        for(int i=0; i < this.sir.length; i ++) {
            if (this.sir[i] == -1) {
                //we will mark in the vector which holds the reached points the new one reached
                //the new last cost of the road will now be set to the peak's value because it represents the cost to reach it
                this.lastCost = min;
                this.sir[i] = nod;
                break;
            }
        }
        //the peak must be eliminated from the queue
        this.varf = this.varf.urm;
    }

    //next functions eliminated an element from a queue based on it's position
    public void elimina(int nod){
        Element el = this.varf;
        Element ant;
        if(el.nod == nod) {
            this.varf = el.urm;
            return;
        }
        while(el!=null) {
            ant = el;
            el = el.urm;
            if(el.nod == nod) {
                ant.urm = el.urm;
                return;
            }
        }
    }

    public boolean esteVida(){
        //the queue is empty when the peak is null
        return this.varf == null;
    }

    public void Preia(PriorityQueue aux){
        //this queue takes the elements from the aux queue
        this.varf = aux.varf;
        this.coada = aux.coada;
    }
    //next function recalculates the values of the elements in the queue after one el was selected
    public void recalculate(){
        Element el = this.varf;
        int lastNod = -1;
        for(int i = 0; i < this.sir.length; i++){
            if(this.sir[i] != -1)
                lastNod = this.sir[i];
        }
        //the recalculation will be made by using an aux queue to store the new elements and browsing the first queue's elements by eliminating
        PriorityQueue aux = new PriorityQueue();
        if(lastNod != -1)
        while(!this.esteVida()) {
            int newDist = -1;
            Street d = g.getStreet(lastNod, el.nod, this);
            //if there s a connection from the point in which we are no to the remaining points we will recalculate
            if(d != null){
                newDist = this.getV().getCost() * d.getCost() + this.getLastCost() + g.costAmbuteiaje(lastNod,el.nod);
                //if the new value is smaller the new value will be added if not the old one will be added
                if(el.val > newDist && newDist != -1) {
                    aux.adauga(newDist, el.nod);
                    this.vec[el.nod] = lastNod;
                    }
                    else {
                        aux.adauga(el.val, el.nod);
                    }
                }
                else {
                    aux.adauga(el.val, el.nod);
                }
            this.elimina(el.nod);
                //move to the next element
            el = el.urm;
        }
        //set the new queue
        this.Preia(aux);
    }
//next class represents an element from the queue

    static class Element{
        private int val;
        private int nod;
        private Element urm;
        public Element() {
            this.val = -1;
            this.urm = null;
        }
        public Element(int x, int nod) {
            this.val = x;
            this.nod = nod;
            this.urm = null;
        }
    }
    void setStart(int cost) {
        this.start = cost;
    }
    int getStart() {
        return start;
    }
    void setLastCost(int cost) {
        this.lastCost = cost;
    }
    int getLastCost() {
        return lastCost;
    }
    void setV(Vehicle v) {
        this.v = v;
    }
    void setGraf(Graph g) {
        this.g = g;
    }
    Graph getGraf(){
        return g;
    }
    Vehicle getV() {
        return v;
    }
    //initializes the array which hols all the passing points
    void initializeSir(int value, int nr) {
        this.sir = new int[nr];
        Arrays.fill(this.sir, -1);
        for(int i = 0; i < this.sir.length; i++)
            if(this.sir[i] == -1) {
                //sets the first point reached to the input
                this.sir[i] = value;
                break;
            }
    }
    //initializes the array which hold all the parents values
    void initializeVec(int nrNodes) {
        this.vec = new int[nrNodes];
        Arrays.fill(this.vec,-1);
    }
    void setEnd(int cost) {
        this.end = cost;
    }
    int getEnd() {
        return end;
    }
}
