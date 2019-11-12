/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho4_VictorCarvalho;

import java.io.*;
import java.net.*;
import java.util.*;

public class Threads extends Thread {

    public Threads() {
        
        start();
    }

    public void run() {

        try {
            while (true) {
                MiniCanvasSample.recebeposicao();
       
            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
