package com.company;

public class Graph {
    Street[] streets;
    int nrNodes;
    int[][] neighboringNodes;
    TrafficJam[] restrictions;

    //next function adds a street in the street array
    void addStreet(int start, int end, int cost, int size) {
        for(int i = 0; i < this.streets.length; i++) {
            if (this.streets[i] == null) {
                //when we meet an empty street we construct the object and add it in his place in the array
                Street n = new Street(cost, size, start, end);
                this.streets[i] = n;
                break;
            }
        }
    }
    //search for a street whose limit it's big enough and if it's found we return it and if not null will be returned
   Street getStreet(int start, int end, PriorityQueue q){
       for (Street street : this.streets) {
           if (street.getStart() == start && street.getEnd() == end &&
                   q.getV().getGauge() <= street.getLimitaGabarit()) {
               return street;
           }
       }
    return null;
   }
   //next function adds all the cost of every Ambuteiaj on the start->end street and returns it
   //Ambuteiaj = TrafficJam
   int costAmbuteiaje(int start, int end){
        int cost = 0;
       for (TrafficJam trafficJam : this.restrictions) {
           if(trafficJam != null)
           if (trafficJam.getEnd() == end && trafficJam.getStart() == start) {
               if (trafficJam instanceof Accident) {
                   cost = cost + ((Accident) trafficJam).getCost();
               }
               if (trafficJam instanceof Jam) {
                   cost = cost + ((Jam) trafficJam).getTimp();
               }
               if (trafficJam instanceof Traffic) {
                   cost = cost + ((Traffic) trafficJam).getTimp();
               }
           }
       }
        return cost;
   }

   //next function adds the Ambuteiaj restrictions in an array
    void addRestriction(String type, int start, int end, int cost){
        int i;
        for(i = 0; i < this.restrictions.length; i++)
            //we need to find the first empty position
            if(this.restrictions[i] == null) {
                break;
            }
        //based on the type of Ambuteiaj we will construct it on the found position
        if(type.equals("accident"))//avem restrictie de viteza
            this.restrictions[i] = new Accident(cost, start, end);
        else
        if(type.equals("trafic"))
            this.restrictions[i] = new Traffic(cost, start, end);
        else
            this.restrictions[i] = new Jam(cost, start, end);
    }
    //constructor who initializes the arrays
    public Graph(int nrNodes, int nrStreets, int nrR){
        this.nrNodes = nrNodes;
        this.neighboringNodes = new int[nrNodes][nrNodes];
        this.streets = new Street[nrStreets];
        this.restrictions = new TrafficJam[nrR];
    }
}
