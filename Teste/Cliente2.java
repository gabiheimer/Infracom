package Teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
public class Cliente2 {

	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		Socket clienteSocket = new Socket("localhost", 3002);
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
		int portaCliente1 = inFromServer.read();
		Node u2 = new Node(InetAddress.getByName("localhost"),portaCliente1);
		while(true) {
			Thread r2 = new Receber(u2,1);
			r2.start();
			Thread s2 = new Enviar(u2,InetAddress.getByName("localhost"),portaCliente1,teclado.nextLine());
			s2.start();
		}
	}

}