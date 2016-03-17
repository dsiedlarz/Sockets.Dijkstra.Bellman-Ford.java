package com.dsiedlarz.zad2;

import java.util.ArrayList;

/**
 * Created by Dawid Siedlarz on 13.10.2015.
 */

/**
 * Klasa reprezetuj¹ algorytm Dijkstry
 */
public class Dijkstra {
    private Graph graph;

    /**
     * Mamy tylko jeden konstrukor, gdy¿ algorytm musi na czymœ dzia³aæ
     * @param graph Wczeœniej utworzony graf
     */
    public Dijkstra(Graph graph) {
        this.graph = graph;
    }


    /**
     * Meriutum ca³ej klasy czyli poszukwianie scie¿ki
     *
     *
     * @param start Poczatek scie¿ki
     * @param end Koniec œcie¿ki
     * @return Najkrótsza œcie¿ke
     */
    public Path findWay(int start, int end){

        long star = System.currentTimeMillis();
        Path path = new Path();
        int a,b,c;
        int n=graph.getV();
        final int[] d=new int[n];
        final int[] p=new int[n];
        int min;
        int index=-1;
        boolean[] Q= new boolean[n];
        ArrayList<Edge> tmp2 = new ArrayList<Edge>();
        //inicjalizacja tablic wag i poprzedników i testowania
        for(int i=0;i<n;i++){
            d[i]=777777777;
            p[i]=-1;
            Q[i]=true;
        }
        d[start]=0;

        for(int i=0;i<n;i++){
            index =0;
            min=777777778;
            /**
             *  Szukanie najmniejszego wagi z nieprzeszukanych jeszcze wierzcho³ków
             */


            for(int j=0;j<n;j++){
                if((min>d[j])&&(Q[j])){
                    index =j;
                    min=d[j];
                }
            }
            Q[index]=false;

        tmp2=graph.getNeighbourhood().get(index) ;
            /**
             * PRZEGLADANIE S¥SIADÓW
             */

        for(int k=0;k<tmp2.size();k++){

            a=tmp2.get(k).getX();
            b=tmp2.get(k).getWeight();
            c=tmp2.get(k).getY();

            /**
             * Dokonywanie zmian w tablicach
             */

        if(d[c]>(d[index]+b)){


            d[c]=(d[index]+b);

            p[c]=index;
        }

        }

        }

        long stop = System.currentTimeMillis();
        /**
         * zapis danych do obiektu typu path
          */
       path.setTime(stop-star);


        path.setWeightSum(d[end]);
        path.getPath().addFirst(end);
        int x=p[end];
        path.getPath().addFirst(x);
        while(x!=start){
            x=p[x];
            path.getPath().addFirst(x);

        }



            return path;
        }


}


