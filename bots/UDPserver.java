import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;

public class UDPserver {

    public static void main(String[] args) throws IOException {
        // Declarando as variaveis
        DatagramSocket serverSocket;
        byte[] rcvData = new byte[1024], sendData;
        InetAddress clientIP;
        DatagramPacket rcvPacket, sendPacket;
        String message;

        // Declarando IP do servidor e Porta do cliente
        serverSocket = new DatagramSocket(5001);
        clientIP = InetAddress.getByName("localhost");
        
        while(true) {
            // Recebendo mensagem
            rcvData = new byte[1024];
            rcvPacket = new DatagramPacket(rcvData, rcvData.length);
            serverSocket.receive(rcvPacket);
            message = new String(rcvPacket.getData(), "UTF-8");

            // Montando resposta
            if(message.contains("Oi")) {
                sendData = ("Oi bb").getBytes();
            } else if(message.contains("Tchau")) {
                sendData = ("NAO VAI POR FAVOR >:(").getBytes();
            } else if(message.contains("Melhor monitor")) {
                sendData = ("Adriano!").getBytes();
            } else if(message.contains("Adriano")) {
                sendData = ("Melhor monitor!").getBytes();
            } else {
                sendData = ("Oi?????").getBytes();
            }

            // enviando resposta
            sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, 4999);
            serverSocket.send(sendPacket);
        }
    }

}
