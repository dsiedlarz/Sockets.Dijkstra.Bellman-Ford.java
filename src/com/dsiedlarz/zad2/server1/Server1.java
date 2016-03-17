package com.dsiedlarz.zad2.server1;

import com.dsiedlarz.zad2.Graph;
import com.dsiedlarz.zad2.Main;
import com.sun.org.apache.regexp.internal.RE;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;


/**
 *  @author Dawid Siedlarz
 * Klasa ob�suguj�ca serwer nr 1.
 * Klasa dzia�a jaka w�tek.
 */
public class Server1 extends Thread{

    private static Graph graph;


     ServerSocket serverSocket;
    Socket clientSocket;
    ObjectOutputStream out;
    BufferedReader in ;

    public Server1() throws IOException {
       graph = new Graph(100,5000,"graph.txt");
        serverSocket =
                new ServerSocket(7000);
        Main.wynik.append(LocalTime.now().toString()+" : "+LocalTime.now().toString()+" : "+"Server no.1 up\n");

    }


    public  void run() {


        XStream xStream = new XStream(new DomDriver());
        String xml= new String();
        xml=xStream.toXML(graph);




    try {

        Main.wynik.append(LocalTime.now().toString()+" : "+"1: Waiting for clients\n");
        clientSocket = serverSocket.accept();
        Main.wynik.append(LocalTime.now().toString()+" : "+"1: Client connected\n");
        out =
                new ObjectOutputStream(clientSocket.getOutputStream());

        in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));


        String inputLine;


        while ((inputLine = in.readLine()) != null) {
            Main.wynik.append(LocalTime.now().toString()+" : "+"1: Receive signal\n");
            resetGraph();
            System.out.println(inputLine);
            Main.wynik.append(LocalTime.now().toString()+" : "+"1: Sending graph serialized to xml\n");
            out.writeObject(xml);



            Main.wynik.append(LocalTime.now().toString()+" : "+"1: Success!\n");
            out.flush();
            in.mark(0);



        }



    } catch (IOException e) {
        System.out.println("Exception caught when trying to listen on port "
                + " or listening for a connection");
        System.out.println(e.getMessage());
    }


    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void close()  {

        try {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
            if (clientSocket != null&&clientSocket.isClosed())
                clientSocket.close();
            if (serverSocket != null&&serverSocket.isClosed())
                serverSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


    public void resetGraph(){
        try {
            graph = new Graph(100,5000,"graph.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




