package Teste;

import java.io.DataInputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Teste1 {

	public static void main(String[] args){
		try {
			ServerSocket socket = new ServerSocket(8888);
			System.out.println("Esperando");
			while (true) {
				Socket tmp = socket.accept();
				tmp.setSoTimeout(10000);
				try {
					System.out.println("Conectado");
					DataInputStream entrada = new DataInputStream(tmp.getInputStream());
					while (true) {
						System.out.println(entrada.readUTF());
					}
				} catch (SocketTimeoutException e) {
					System.out.println(e.getMessage());
					tmp.close();
				}
			}
		} catch (BindException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
