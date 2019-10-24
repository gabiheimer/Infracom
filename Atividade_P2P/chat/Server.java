package chat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket; 
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import chat.*;

public class Server{
    public static void main( String args[]) throws Exception { 
        String message; 
        String answer; 
        ServerSocket socket = new ServerSocket(6789);       
        Socket connection;    
        Scanner inFromClient;
        DataOutputStream outToClient;
        Client c1 = null, c2 = null;
        int p1 = 0, p2 = 0;
        int qttClients = 0;

        System.out.println("Server is running!");

        while(true){
            connection = socket.accept();
            qttClients += 1;

            if(qttClients == 1){
                System.out.println("Client 1 is online!");
                c1 = new Client(connection.getInetAddress().getHostAddress());
                //p1 = connection.getPort();
            } else {
                System.out.println("Client 2 is online!");
                c2 = new Client(connection.getInetAddress().getHostAddress());
                //p2 = connection.getPort();
                c1.connectToClient(c2.myIp, 1234);
                c2.connectToClient(c1.myIp, 1234);
            }
            System.out.println("Closing connection :(");
            connection.close();
        }
    
    }

}
