package com.dsiedlarz.zad2;

/**
 * Created by Dawid Siedlarz on 13.10.2015.
 */

import java.io.Serializable;

/**
 * Klasa reprezentuj¹ca krawedz grafu
 *
 */
public class Edge implements Serializable  {
    /**
     * Odpowiednio:
     * x-pocz¹tek
     * y-koniec
     * weight-waga
     */
    private int x,y, weight;

    /**
     * Tworzy pusta krawedz
     */
    public Edge() {
    }

    /**
     * Tworzy krawedz
     * @param x poczatek
     * @param weight waga
     * @param y koniec
     */
    public Edge(int x, int weight, int y) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {

        return weight;
    }

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        String s= new String();
        s=x+","+weight+","+y;
        return s;

    }

}
