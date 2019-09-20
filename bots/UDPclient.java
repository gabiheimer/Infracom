import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.io.IOException;

public class UDPclient {

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);

        // Declarando variaveis
        byte[] sendData, rcvData = new byte[1024];
        DatagramSocket clientSocket;
        DatagramPacket sendPacket, rcvPacket;
        InetAddress serverIP;
        String messageSend, messageRcv;
        long t1, t2, rtt;
        
        // Declarando IP do cliente e Porta do servidor
        clientSocket = new DatagramSocket(4999);
        serverIP = InetAddress.getByName("localhost");

        while(true) {
            // Enviando a mensagem
            System.out.print("Diga alguma coisa: ");
            messageSend = in.nextLine();
            sendData = (messageSend).getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, 5001);
            clientSocket.send(sendPacket);
            t1 = System.currentTimeMillis();

            // Recebendo a resposta
            rcvData = new byte[1024];
            rcvPacket = new DatagramPacket(rcvData, rcvData.length);
            clientSocket.receive(rcvPacket);
            t2 = System.currentTimeMillis();

            // Calculando RTT
            rtt = t2 - t1;

            // Printando mensagem e dados
            messageRcv = new String(rcvPacket.getData(), "UTF-8");

            System.out.println("Resposta do Servidor: " + messageRcv);
            System.out.println("t1: " + t1 + "ms");
            System.out.println("t2: " + t2 + "ms");
            System.out.println("RTT: " + rtt + "ms\n");
        }
    }
}

