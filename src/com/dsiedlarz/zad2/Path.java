package com.dsiedlarz.zad2;

import java.util.LinkedList;

/**
 * Created by Dawid Siedlarz on 14.10.2015.
 */

/**
 *Klasa reprezentuj�ca wynikow� najkr�tsza �cie�ke
 */
public class Path {
    private int weightSum;
    private LinkedList<Integer> path;
    private long time;

    public Path() {
        path= new LinkedList<Integer>();
    }

    public int getWeightSum() {
        return weightSum;
    }

    public LinkedList<Integer> getPath() {
        return path;
    }

    public void setWeightSum(int weightSum) {
        this.weightSum = weightSum;
    }


    public String toString(){
        String s=new String();

        s="Weight sum: "+weightSum+"\n";
        s+=path.get(0);
        for(int i=1;i<path.size();i++){
            s+="--";
            s+=path.get(i);

        }
        s+="\nFound in: "+time+" milis\n";

        return s;
    }

    public String weightToString(){
        String s=new String();
        s="Waga sciezki wynosi: "+weightSum+"          \n";

        return s;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String pathToString(){
        String s=new String();
        s="";
        s+=path.get(0);
        for(int i=1;i<path.size();i++){
            s+="--";
            s+=path.get(i);

        }

        return s;
    }

}
