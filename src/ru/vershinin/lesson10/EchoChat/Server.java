package ru.vershinin.lesson10.EchoChat;

/**
 * Server
 *
 * @author Вершинин Пётр
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread {
    public final static int PORT = 7331;
    private final static int BUFFER = 1024;

    private final DatagramSocket socket;
    private final ArrayList<InetAddress> clientAddresses;//адресс елиента
    private final ArrayList<Integer> clientPorts;// порт клиента
    private final HashSet<String> existingClients;// информация о клиенте
    public Server() throws IOException {
        socket = new DatagramSocket(PORT);
        clientAddresses = new ArrayList();
        clientPorts = new ArrayList();
        existingClients = new HashSet();
    }

    public void run() {
        byte[] buf = new byte[BUFFER];//размерность
        while (true) {
            try {
                Arrays.fill(buf, (byte)0);// назначаем всем элементам тип Byte
                DatagramPacket packet = new DatagramPacket(buf, buf.length);//конструктор без информации об адрессе и порте
                socket.receive(packet);//получаем пакет

                String content = new String(buf, buf.length);

                InetAddress clientAddress = packet.getAddress();//получаем ip
                int clientPort = packet.getPort();//получаем порт

                String id = clientAddress.toString() + "," + clientPort;
                if (!existingClients.contains(id)) {
                    existingClients.add( id );// если нет такого id добавляем в existingClients
                    clientPorts.add( clientPort );
                    clientAddresses.add(clientAddress);// если нет такого ip добавляем в set
                }
                System.out.println(id + " : " + content);
                //byte[] data = (id + " : " +  content).getBytes();
                byte[] data = (content).getBytes();
                for (int i=0; i < clientAddresses.size(); i++) {
                    InetAddress cl = clientAddresses.get(i);
                    int cp = clientPorts.get(i);
                    packet = new DatagramPacket(data, data.length, cl, cp);
                    socket.send(packet);
                }
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Server s = new Server();
        s.start();
    }
}
