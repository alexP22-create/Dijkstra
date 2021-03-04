package com.company;
import java.io.*;
import java.util.Scanner;
//POSTOLACHE Alexandru-Gabriel 321CB

public class Main {
    //urm functie primeste o linie din fis de input
    static void aux(String line, Graph g) {
        String[] words = line.split(" ");
        //it it starts with P the line is street
        if (words[0].startsWith("P")){
            words[0] = words[0].substring(1);
            int start = Integer.parseInt(words[0]);
            words[1] = words[1].substring(1);
            int end = Integer.parseInt(words[1]);
            //we will mark in the matrix that there is a street which connects the start point with the end point
            g.neighboringNodes[start][end] = 1;
            int cost = Integer.parseInt(words[2]);
            int gauge = Integer.parseInt(words[3]);
            //we add the street in the street's array
            g.addStreet(start, end, cost, gauge);
        }
        else {
            if(!words[0].equals("drive")) {
                String type = words[0];
                words[1] = words[1].substring(1);
                int start = Integer.parseInt(words[1]);
                words[2] = words[2].substring(1);
                int end = Integer.parseInt(words[2]);
                int cost = Integer.parseInt(words[3]);
                //we add the Ambuteiaj restriction in it's array
                g.addRestriction(type, start, end, cost);
            }
        }
    }

    //search for a street regardless of it's limit in the street's array and returns it
    public static Street getStrada(Graph g, int start, int end) {
        for(int i = 0; i < g.streets.length; i++)
            if(g.streets[i].getStart() == start && g.streets[i].getEnd() == end)
                return g.streets[i];
        return null;
    }

    static PriorityQueue setPriorityQueue(Graph g, int start, int end, String v){
        PriorityQueue q = new PriorityQueue();
        q.initializeSir(start, g.nrNodes);
        q.initializeVec(g.nrNodes);
        q.setEnd(end);
        q.setStart(start);
        q.setGraf(g);
        //based on input we create the vehicle's object and set it to the priority queue
        if(v.equals("b")) {
            Bicycle b = new Bicycle();
            q.setV(b);
        }
        if(v.equals("c")) {
            Truck b = new Truck();
            q.setV(b);
        }
        if(v.equals("a")) {
            Car b = new Car();
            q.setV(b);
        }
        if(v.equals("m")) {
            Motorcycle b = new Motorcycle();
            q.setV(b);
        }
        //if matrix[i][j] = 1 it means there's a connection between point i and point j
        for(int j = 0; j < g.nrNodes; j ++) {
            //if there is not a connection between i and j or the founded street's limit is too small we will ad
            // the element with a "infinite" value which represents in Dijkstra that it can't be reached yet
            if(g.neighboringNodes[start][j] == 1) {
                //add the element in the priority queue
                Street d = getStrada(g, start, j);
                if(d != null && d.getLimitaGabarit() >= q.getV().getGauge())
                    q.adauga(d.getCost() * q.getV().getCost() + g.costAmbuteiaje(start, j), j);
                else
                    if(d != null)
                        q.adauga(PriorityQueue.infinite, j);
            }
            else
            if(j != start)
                q.adauga(PriorityQueue.infinite, j);
        }
        return q;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("map.in");
        //first we need to empty the file in which we want to write
        PrintWriter pr = new PrintWriter("map.out");
        pr.print("");
        pr.close();
        //we use count to count every input line which gives us information
        int count = 0;
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String b = scan.nextLine();
            String[] word = b.split(" ", 2);
            if(!word[0].equals("drive"))
                count ++;
        }
        scan.close();
        scan = new Scanner(file);
        //nr of streets
        int nrS = scan.nextInt();
        //nr of nodes
        int nrN = scan.nextInt();
        scan.nextLine();//because nextInt doesn't read \n
        int nrA = count - nrS -1;//number of Ambuteiaje
        Graph g = new Graph(nrN, nrS, nrA);
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            //aux function reads all the information given based on the type of the line
            aux(line, g);
            //when line starts with "drive" it's a command
            if(line.startsWith("drive")){
                String[] words = line.split(" ", 4);
                words[2] = words[2].substring(1);
                int start = Integer.parseInt(words[2]);
                words[3] = words[3].substring(1);
                int end = Integer.parseInt(words[3]);
                //next function is creating the priority queue
                PriorityQueue q = setPriorityQueue(g, start, end, words[1]);
                //nou we use the drive function which will write the fastest way to go from start to end
                q.drive(q.getV(), start, end);
            }
        }
        scan.close();
    }
}
