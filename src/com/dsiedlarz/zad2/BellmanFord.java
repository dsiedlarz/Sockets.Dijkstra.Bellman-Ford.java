package com.dsiedlarz.zad2;

/**
 * Created by Dawid Siedlarz on 16.10.2015.
 */


/**
 *Klasa reprezentujaca realizacje algorytmu Bellmana-Forda
 */
public class BellmanFord {
    private Graph graph;

    /**
     * Mamy tylko jeden konstrukor, gdy¿ algorytm musi na czymœ dzia³aæ
     * @param graph Wczeœniej utworzony graf
     */
    public BellmanFord(Graph graph) {
        this.graph = graph;
    }


    /**
     * Meriutum ca³ej klasy czyli poszukwianie scie¿ki
     *
     *
     * @param start Poczatek scie¿ki
     * @param end Koniec œcie¿ki
     * @return Najkrótsza œcie¿ke
     * @throws Exception polegaj¹cy na ujemnym cyklu
     */
    public Path findWay(int start,int end) throws Exception {
        long star = System.currentTimeMillis();
        Path path = new Path();
        boolean test=true;
        int n=graph.getV();
        int d[]=new int[n];
        int p[]=new int[n];
        for (int i=0;i<n;i++){
            d[i]=77777777;
            p[i]=-1;
        }
        d[start]=0;

        for(int i=0;i<n-1;i++){
            test=true;
                for(int j=0;j<n;j++){
                    for(int k=0;k<graph.getNeighbourhood().get(j).size();k++){
                        if(d[graph.getNeighbourhood().get(j).get(k).getY()]>(d[graph.getNeighbourhood().get(j).get(k).getX()]+
                                                                            graph.getNeighbourhood().get(j).get(k).getWeight()) ){
                            /**
                             * zostanie wykonana zmiana wiec pêtla przekrêci sie jeszcze raz
                             */
                            test=false;
                            d[graph.getNeighbourhood().get(j).get(k).getY()]=(d[graph.getNeighbourhood().get(j).get(k).getX()]+
                                    graph.getNeighbourhood().get(j).get(k).getWeight());
                            p[graph.getNeighbourhood().get(j).get(k).getY()]=graph.getNeighbourhood().get(j).get(k).getX();
                        }
                    }
                }


            if(test) break;

        }
        /**
         * Szuaknie ujemnego cyklu
         */

        for(int j=0;j<n;j++){
            for(int k=0;k<graph.getNeighbourhood().get(j).size();k++){
                if(d[graph.getNeighbourhood().get(j).get(k).getY()]>(d[graph.getNeighbourhood().get(j).get(k).getX()]+
                        graph.getNeighbourhood().get(j).get(k).getWeight()) ){

                  throw new Exception("Ujemny cykl");
                }
            }
        }
        long stop = System.currentTimeMillis();
        /**
         * Konwersja danych do obiektu dypu path
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
