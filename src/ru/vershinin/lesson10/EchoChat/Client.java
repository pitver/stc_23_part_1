package ru.vershinin.lesson10.EchoChat;

/**
 * Client
 *
 * @author Вершинин Пётр
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


class MessageSender implements Runnable {
    public final static int PORT = 7331;
    private final DatagramSocket sock;
    private final String hostname;
    private final String nik;

    MessageSender(DatagramSocket s, String h, String nik) {
        this.sock = s;
        this.hostname = h;
        this.nik = nik;

    }

    /**
     * отправка сообщений  на сервер
     *
     * @param textMessage- текст сообщения
     * @throws Exception
     */
    private synchronized void sendMessage(String textMessage) throws Exception {
        byte[] buf = textMessage.getBytes();
        InetAddress address = InetAddress.getByName(hostname);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        sock.send(packet);
    }

    public void run() {
        boolean connected = false;
        do {
            try {
                sendMessage(nik + ": " + "connection to the server is successful");
                connected = true;
            } catch (Exception e) {

            }
        } while (!connected);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = null;
            try {
                while (!in.ready()) {
                    Thread.sleep(500);
                }
                line = in.readLine();
                if (!line.equals("quit")) {
                    sendMessage(nik + ": " + line);
                } else {
                    closeSocket();
                    return;
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    protected void closeSocket() {
        try {
            sendMessage(nik + ": out");

        } catch (Exception e) {
            e.printStackTrace();
        }
        sock.close();
       // System.exit(1);
    }
}

class MessageReceiver implements Runnable {
    DatagramSocket sock;
    byte[] buf;

    MessageReceiver(DatagramSocket s) {
        sock = s;
        buf = new byte[1024];
    }

    /**
     * чтение сообщений с сервера
     */
    public void run() {
        while (true) {
            try {
                //if (buf.equals())
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                sock.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                if (!received.contains("quit")) {
                    System.out.println(received);
                } else {
                    System.out.println("client out");
                    return;
                }

            } catch (Exception e) {

                System.err.println(e);
                return;
            }
        }
    }
}

public class Client {

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        DatagramSocket socket = null;
        String name = "test1";


        socket = new DatagramSocket();

        MessageReceiver r = new MessageReceiver(socket);
        MessageSender s = new MessageSender(socket, host, name);

        Thread rt = new Thread(r);
        Thread st = new Thread(s);
        rt.start();
        st.start();


    }

}
