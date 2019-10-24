package chat;

import java.net.ServerSocket;
import java.net.Socket; 
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.net.UnknownHostException;
import java.io.IOException;
import chat.*;

public class Client {
	Scanner inClient, in = new Scanner(System.in);
	String message, server, answer, client, myIp;
	int port;
	Socket client_server, client_client;
	DataOutputStream outClient;
	
	public Client(String ip){
		this.myIp = ip;
	}

	void connectToServer(String serverIP, int port) throws UnknownHostException, IOException{
		client_server = new Socket(serverIP, port);
	}

	void connectToClient(String clientIP, int port)throws UnknownHostException, IOException{
		client_client = new Socket(clientIP, port);
		Thread rcv = new Rcv_Thread(this);
		Thread snd = new Send_Thread(this);
		rcv.start();
		snd.start();
	}
	
	void send() throws 	IOException {
		while(true) {
			outClient = new DataOutputStream(client_client.getOutputStream());
			message = in.nextLine();
			outClient.writeBytes(message + '\n');
		}
	}
	
	void receive() throws IOException{
		while(true) {
			inClient = new Scanner(new InputStreamReader(client_client.getInputStream()));
			answer = inClient.nextLine();
			System.out.println(answer);
		}
	}
}