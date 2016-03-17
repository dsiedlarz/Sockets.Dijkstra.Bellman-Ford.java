package com.dsiedlarz.zad2;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dawid Siedlarz on 14.10.2015.
 */


/**
 * Klasa reprezentuj¹ca graf
 *
 */
public class Graph implements Serializable {
    private int v,e;
    String fileName;
    private ArrayList<ArrayList<Edge>> neighbourhood;

    /**
     * Konstruktor otwieraj¹cy uprzedniu utworzony graf
     *
     * @param fileName Nazwa pliku
     * @throws IOException B³¹d wczytania z pliku(brak dostêpu lub brak pliku)
     */
    public Graph(String fileName) throws IOException {
        this.fileName=fileName;
        neighbourhood= new ArrayList<ArrayList<Edge>>();
        String w;
        String[] tmp=new String[3];
        FileInputStream is = new FileInputStream(fileName);
        InputStreamReader r = new InputStreamReader(is);
        BufferedReader b = new BufferedReader(r);
        v=Integer.parseInt(b.readLine());
        e=Integer.parseInt(b.readLine());
        for(int l=0;l<v;l++){
            neighbourhood.add(new ArrayList<Edge>());
        }

        while ((w = b.readLine()) != null) {
            tmp=w.split(",");
            neighbourhood.get(Integer.parseInt(tmp[0])).add(new Edge(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]),Integer.parseInt(tmp[2])));

        }
    }

    /**
     * Tworzy nowy graf i zapisuje go do pliku
     *
     *
     * @param v iloœc wierzcho³ków
     * @param e iloœæ krawedzi
     * @param fileName nazwa pliku docelowego
     * @throws IOException B³¹d odczytu pliku
     */
    public Graph(int v, int e, String fileName) throws IOException {
        this.fileName=fileName;
        this.v = v;
        this.e = e;
        neighbourhood= new ArrayList<ArrayList<Edge>>();
        int a,b,weight;
        Random generator= new Random();
        FileOutputStream os=new FileOutputStream(fileName);
        OutputStreamWriter writer = new OutputStreamWriter(os);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for(int l=0;l<v;l++){
            neighbourhood.add(new ArrayList<Edge>());
        }

        bufferedWriter.write(v+"");
        bufferedWriter.newLine();
        bufferedWriter.write(e+"");
        bufferedWriter.newLine();
        for (int i=0;i<e;i++){
            a=generator.nextInt(v);
            b=generator.nextInt(v);
            weight=generator.nextInt(149)+1;
            neighbourhood.get(a).add(new Edge(a,weight,b));
            bufferedWriter.write(( a + "," + weight + "," + b));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();

    }


    /**
     *Metoda pomocnicza drukuj¹ca graf w konsoli
     */
    public void print(){
        ArrayList<Edge> tmp= new ArrayList<Edge>();
        System.out.println("Wierzcholki: "+v);
        System.out.println("Krawedzi: "+e);
        for(int i=0;i<v;i++){
         tmp=neighbourhood.get(i);
            for (int j = 0;j<tmp.size();j++){
                System.out.println(tmp.get(j).toString());
            }

        }

    }


    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    public ArrayList<ArrayList<Edge>> getNeighbourhood() {
        return neighbourhood;
    }


    public void saveToFile() throws IOException {
        int t;
        FileOutputStream os=new FileOutputStream(fileName);
        ArrayList<Edge> tmp = new ArrayList<Edge>();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(v+"");
        bufferedWriter.newLine();
        bufferedWriter.write(e+"");
        bufferedWriter.newLine();
        for (int i=0;i<v;i++){
            tmp=neighbourhood.get(i);
            t=tmp.size();

            for(int j=0;j<t;j++) {

                bufferedWriter.write((tmp.get(j).toString()));
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();

    }
}
