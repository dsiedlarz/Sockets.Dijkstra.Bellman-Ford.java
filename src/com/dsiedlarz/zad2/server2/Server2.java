package com.dsiedlarz.zad2.server2;


import com.dsiedlarz.zad2.Graph;
import com.dsiedlarz.zad2.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;


/**
 * @author Dawid Siedlarz
 * Klasa ob�suguj�ca serwer nr 2.
 * Klasa dzia�a jaka w�tek.
 */

public class Server2 extends Thread {

    private Graph graph;

    ServerSocket serverSocket;
    Socket clientSocket;
    ObjectOutputStream out;
    BufferedReader in ;


   public Server2() throws IOException {
        graph = new Graph(100,5000,"graph.txt");
       serverSocket = new ServerSocket(7001);
       Main.wynik.append(LocalTime.now().toString()+" : "+"Server no.2 up\n");
    }

    public void resetGraph(){
        try {
            graph = new Graph(100,5000,"graph.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {

            Main.wynik.append(LocalTime.now().toString()+" : "+"2: Waiting for clients\n");
            clientSocket = serverSocket.accept();
            Main.wynik.append(LocalTime.now().toString()+" : "+"2: Client connected\n");
                ObjectOutputStream out =
                        new ObjectOutputStream(clientSocket.getOutputStream());
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                Main.wynik.append(LocalTime.now().toString()+" : "+"2: Receive signal\n");
                resetGraph();
                System.out.println(inputLine);
                Main.wynik.append(LocalTime.now().toString()+" : "+"2: Sending graph as serializable object\n");
                out.writeObject(graph);
                Main.wynik.append(LocalTime.now().toString()+" : "+"2: Success!\n");
                out.flush();
                in.mark(0);
            }



        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " +
                   " or listening for a connection");
            System.out.println(e.getMessage());

        }
        finally {



        }


}


    @Override
    public void interrupt() {
        super.interrupt();
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
}


