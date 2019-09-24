package com.company;

public class Player implements Comparable<Player>{

    private String name ; // player name
    private int time ; // time in millis

    public Player(String name , int time) {
        this.name = name;
        this.time = time;
    }


    public void setTime(int time) {
        this.time = time;
    }

    public int getTime(){
        return this.time;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public int compareTo(Player pl){  // stuff fot Collection.sort by time
        if(this.time == pl.getTime()){
            return 0;
        }else if(this.time > pl.getTime()){
            return 1;
        }else {
            return -1;
        }
    }
}
