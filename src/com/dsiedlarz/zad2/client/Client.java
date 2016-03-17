package com.dsiedlarz.zad2.client;

import com.dsiedlarz.zad2.Dijkstra;
import com.dsiedlarz.zad2.Graph;
import com.dsiedlarz.zad2.Main;
import com.dsiedlarz.zad2.Path;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;


/**
 * @author Dawid Siedlarz
 * Klasa ob�suguj�ca serwer nr 2.
 * Klasa dzia�a jaka w�tek.
 */

public class Client extends Thread
{

    int i;
  private Graph graph;
    private String path;
    Socket echoSocket;
    PrintWriter out;
    ObjectInputStream in;
    Socket echoSocket2;
    PrintWriter out2;
    ObjectInputStream in2;

    public Client(int i) throws IOException {
        this.i=i;
        graph = new Graph(1,1,"graph.txt");


        echoSocket = new Socket("localhost", 7000);
         out =
                new PrintWriter(echoSocket.getOutputStream(), true);
         in=new ObjectInputStream(echoSocket.getInputStream());




        echoSocket2 = new Socket(("localhost"), 7001);
             out2 =
                new PrintWriter(echoSocket2.getOutputStream(), true);
        in2=new ObjectInputStream(echoSocket2.getInputStream());
    }

    public String getPath() {
        return path;
    }



    public void firstServer(){
        System.out.println("Asdad");
        XStream xStream = new XStream(new DomDriver());
        String xml = new String();
        Main.wynik.append(LocalTime.now().toString()+" : "+"Client:Sending signal to server no.1\n");
        out.println(i);
        Main.wynik.append(LocalTime.now().toString()+" : "+"Client:Waitng for transmision\n");

        try {
            xml = (String) in.readObject();
        } catch (IOException e) {
            Main.wynik.append(LocalTime.now().toString()+" : "+"Client:Something went wrong with IO\n");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            Main.wynik.append(LocalTime.now().toString()+" : "+"Client:Class not found\n");
            e.printStackTrace();
        }
        Main.wynik.append(LocalTime.now().toString()+" : "+"Client:Success!\n");
        graph = (Graph) xStream.fromXML(xml);

        Dijkstra dijkstra = new Dijkstra(graph);
        Main.wynik.append(LocalTime.now().toString()+" : "+dijkstra.findWay(1,5).toString());


       out.flush();

    }


    public void secondGraph(){

        Main.wynik.append(LocalTime.now().toString()+" : "+"Client:Sending signal to server no.2\n");
        out2.println("2");
        Main.wynik.append(LocalTime.now().toString()+" : "+"Client:Waitng for transmision\n");
        try {
            graph = (Graph) in2.readObject();
        } catch (IOException e) {
            Main.wynik.append(LocalTime.now().toString()+" : "+"Client:Something went wrong with IO\n");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            Main.wynik.append(LocalTime.now().toString()+" : "+"Client:Class not found\n");
            e.printStackTrace();
        }
        Main.wynik.append(LocalTime.now().toString()+" : "+"Client:Success!\n");

        Dijkstra dijkstra = new Dijkstra(graph);
        path=dijkstra.findWay(1,5).toString();
        Main.wynik.append(LocalTime.now().toString()+" : "+dijkstra.findWay(1,5).toString());

    }



}