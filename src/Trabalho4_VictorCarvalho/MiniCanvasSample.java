package Trabalho4_VictorCarvalho;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class MiniCanvasSample extends JPanel {

    static private String username = "";
    static private int x = 0;
    static private int y = 0;
    private DatagramPacket packet;
    private InetAddress address;
    private byte[] posicao;

    static Jogador player;
    static ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

    private static MulticastSocket workingSocket;
    static final int MULTICAST_PORT = 4446;
    static final String MULTICAST_IP_ADDRESS = "230.0.0.1";
    InetAddress group = null;

    public MiniCanvasSample() {
        //Monitora eventos de teclado

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                MoveObject(e);
            }
        });

        try {

            workingSocket = new MulticastSocket(MULTICAST_PORT);
            group = InetAddress.getByName(MULTICAST_IP_ADDRESS);
            workingSocket.joinGroup(group);
            new Threads(workingSocket, this);
            System.out.println("entrou no grupo");

        } catch (Exception err) {
            System.out.println("minicanvas: " + err);
        }

    }

    //Atualizacao da tela com o nome do usuario na posicao x,y
    public void paint(Graphics g) {
        //Obtem o foco para obter eventos de teclado
        requestFocus();
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        //Desenha o nome do usuario

       // g.drawString(username, x, y);
        
        for(Jogador j : jogadores){
            g.drawString(j.getNome(), j.getX(), j.getY());
        }
    }

    public void msgThread(Jogador player) {
        boolean jatem = false;
        for (Jogador j : jogadores) {
            if (j.getNome().equals(player.getNome())) {
                j.setX(player.getX());
                j.setY(player.getY());
                jatem = true;
            }
        }
        if (!jatem) {
            jogadores.add(player);
        }
        repaint();
    }

    //Controla acoes do teclado
    public void MoveObject(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int offset = 5;
        switch (keyCode) {
            case KeyEvent.VK_UP:
                y = y - offset;
                break;
            case KeyEvent.VK_DOWN:
                y = y + offset;
                break;
            case KeyEvent.VK_LEFT:
                x = x - offset;
                break;
            case KeyEvent.VK_RIGHT:
                x = x + offset;
                break;
        }
        String aux = "";

        aux += username + " ";
        aux += x + " ";
        aux += y;

        System.out.println(aux);
        try {
            System.out.println(jogadores);
            address = InetAddress.getByName(MULTICAST_IP_ADDRESS);
            packet = new DatagramPacket(aux.getBytes(), aux.getBytes().length, address, MULTICAST_PORT);
            ///packet = new DatagramPacket(posicao(username, x, y), posicao(username, x, y).length, address, MULTICAST_PORT);
            workingSocket.send(packet);

            System.out.println("enviou a mensagem");
        } catch (Exception err) {
            System.out.println("move: " + err);
        }

        //Redesenha com a nova posi��o
        //repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mini Canvas Sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MiniCanvasSample mCanvas = new MiniCanvasSample();
        frame.add(mCanvas);
        frame.setSize(300, 200);
        //Nome do usuario gerado aleatoriamente
        Random rnd = new Random();
        username = Integer.toString(rnd.nextInt(50));
        //Posi��o inicial do texto
        x = 300 / 2;
        y = 200 / 2;
        frame.setVisible(true);
        player = new Jogador(username, x, y);
        jogadores.add(player);
    }
}
