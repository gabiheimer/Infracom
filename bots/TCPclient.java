import java.net.ServerSocket;
import java.net.Socket; 
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

class TCPclient {

    public static void main(String args[]) throws Exception {

        // Declarando variáveis
        Scanner inFromServer, in = new Scanner(System.in);
        String message, server = "localhost", answer;
        int port = 6789;
        Socket clientSocket;
        DataOutputStream outToServer;
        long t1, t2, rtt;

        // conectando cliente e servidor
        clientSocket = new Socket(server, port);

        while(true){

            // variavel que vai realizar o envio da mensagem para o servidor 
            outToServer = new DataOutputStream(clientSocket.getOutputStream());

            // variavel que vai realizar a leitura do que chega do servidor 
            inFromServer = new Scanner(new InputStreamReader(clientSocket.getInputStream()));

            // enviado a mensagem
            System.out.print("Diga alguma coisa: ");
            message = in.nextLine();
            outToServer.writeBytes(message + '\n');
            t1 = System.currentTimeMillis();

            // recebendo a resposta
            answer = inFromServer.nextLine();
            t2 = System.currentTimeMillis();

            // Calculando RTT
            rtt = t2 - t1;

            // printando resposta e dados
            System.out.println("Resposta do Servidor: " + answer);
            System.out.println("t1: " + t1 + "ms");
            System.out.println("t2: " + t2 + "ms");
            System.out.println("RTT: " + rtt + "ms\n");
        }

    }
}