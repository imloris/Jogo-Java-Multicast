/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho4_VictorCarvalho;

import java.awt.Rectangle;
import java.io.*;
import java.net.*;
import java.util.*;

public class Threads extends Thread {

    MulticastSocket workingSocket;
    MiniCanvasSample mc;

    public Threads(MulticastSocket workingSocket, MiniCanvasSample mc) {
        this.workingSocket = workingSocket;
        this.mc = mc;

        start();
        System.out.println("startou a thread");
    }

    public void run() {

        try {
            while (true) {
                byte array[] = new byte[1000];

                DatagramPacket packet = new DatagramPacket(array, array.length);
                workingSocket.receive(packet);
                String novaposicao = new String(packet.getData()).trim();

                String arrayString[] = novaposicao.split(" ");

                Jogador player = new Jogador(arrayString[0], Integer.parseInt(arrayString[1]), Integer.parseInt(arrayString[2]));

                mc.msgThread(player);

            }

        } catch (Exception e) {
            System.err.println("thread  " + e);
        }

    }
}
