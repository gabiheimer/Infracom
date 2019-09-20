import java.net.ServerSocket;
import java.net.Socket; 
import java.util.Scanner;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
class TCPserver { 
     public static void main( String args[]) throws Exception { 

         // Declarando variaveis
         String message; 
         String answer; 
         ServerSocket socket = new ServerSocket(6789);       
         Socket conecction;    
         Scanner inFromClient;
         DataOutputStream outToClient;
                     
        // formando conexao com cliente
        conecction = socket.accept();

         while (true) { 
            
            // variavel que vai realizar a leitura do que chega do cliente                                                      
            inFromClient = new Scanner( new InputStreamReader(conecction.getInputStream()));

            // variavel que vai realizar o envio da resposta para o cliente 
            outToClient = new DataOutputStream(conecction.getOutputStream());

            // lendo mensagem e formando resposta                                      
            message = inFromClient.nextLine(); 
            if(message.contains("Oi")) {
                answer = "Oi bb";
            } else if(message.contains("Tchau")) {
                answer = "NAO VAI POR FAVOR >:(";
            } else if(message.contains("Melhor monitor")) {
                answer = "Adriano!";
            } else if(message.contains("Adriano")) {
                answer = "Melhor monitor!";
            } else {
                answer = "Oi?????";
            }
            
            // enviando resposta
            outToClient.writeBytes(answer + '\n'); 
        } 
     } 
}

