package Teste;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Teste2 {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8888);
			Scanner teclado = new Scanner(System.in);
			DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
			while (true) {
				saida.writeUTF(teclado.nextLine());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
