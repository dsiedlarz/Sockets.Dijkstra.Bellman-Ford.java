package com.dsiedlarz.zad2;

/**
 * Created by Dawid on 09.11.2015.
 */

import com.dsiedlarz.zad2.client.Client;
import com.dsiedlarz.zad2.server1.Server1;
import com.dsiedlarz.zad2.server2.Server2;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalTime;


/**
 * Created by Dawid Siedlarz on 13.10.2015.
 */

/**
 * G��wna klasa w kt�rej zawarta jest implementacja realizacja GUI
 */
public class Main extends JFrame {
    private Graph graph;
    Server1 server1;
    Server2 server2;
    Client client;

    private JButton
            b1 = new JButton("Turn on servers"),

    b3 = new JButton("Download graph from 1st server.(xStream)"),
            b4 = new JButton("Download graph from 2nd server.(Serializable)");
    public static JTextArea wynik = new JTextArea();
    private JScrollPane jScrollPane;

    class ButtonListener1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (server1 != null && server1.isAlive() && server2 != null && server2.isAlive())
                wynik.append(LocalTime.now().toString()+" : "+"Servers already up");
            else {
                wynik.append(LocalTime.now().toString()+" : "+"Turning on server no. 1 and no. 2\n");
                try {
                    server1 = new Server1();
                    server2 = new Server2();
                } catch (IOException e1) {
                    wynik.append(LocalTime.now().toString()+" : "+"Ups... Something went wrong\n");
                    e1.printStackTrace();
                }
                server1.start();
                server2.start();
                try {
                    client = new Client(1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    class ButtonListener3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(client!=null)
            client.firstServer();
            else
                wynik.append(LocalTime.now().toString()+" : "+"Turn on server, please");
        }
    }


    class ButtonListener4 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(client!=null)
                client.secondGraph();
            else
                wynik.append(LocalTime.now().toString()+" : "+"Turn on server, please");
        }
    }

    private ButtonListener1 bl1 = new ButtonListener1();
    private ButtonListener3 bl3 = new ButtonListener3();
    private ButtonListener4 bl4 = new ButtonListener4();


    public Main() throws IOException {
        wynik.setRows(20);
        wynik.setColumns(30);
        jScrollPane = new JScrollPane(wynik);
        DefaultCaret caret = (DefaultCaret)wynik.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);


        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Are you sure to close?");
                if (i == 0) {
                    if (server1 != null) server1.close();
                    if (server2 != null) server2.close();
                    System.exit(0);
                }
            }
        });

        b1.addActionListener(bl1);
        b3.addActionListener(bl3);
        b4.addActionListener(bl4);
        setTitle("Shortest path");
        setLayout(new FlowLayout());
        add(b1);

        add(b3);
        add(b4);
        add(jScrollPane);

        setVisible(true);
    }


    public static void main(String[] args)

    {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    new Main();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}