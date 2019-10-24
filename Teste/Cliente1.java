package Teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente1 {

	public static void main(String[] args) throws IOException{
		Scanner teclado = new Scanner(System.in);
		Socket clienteSocket = new Socket("localhost", 3001);
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
		int portaCliente2 = 3001;
		Node u1 = new Node(InetAddress.getByName("localhost"),portaCliente2);
		while(true) {
			Thread r1 = new Receber(u1,2);
			r1.start();
			Thread s1 = new Enviar(u1,InetAddress.getByName("localhost"),portaCliente2,teclado.nextLine());
			s1.start();
		}

	}

}
class Node {
	int portaSegundoNode;
	DatagramSocket nodeSocket;

	public Node(InetAddress nodeIP, int portaSegundoNode) throws IOException{
		this.portaSegundoNode = portaSegundoNode;
		nodeSocket = new DatagramSocket();
	}

	void enviar(InetAddress segundoNodeIP, int portaSegundoNode, String texto) throws IOException {
		byte[] out = texto.getBytes();
		Object lock1 = new Object();
		DatagramPacket sendPacket = new DatagramPacket(out, out.length, segundoNodeIP, portaSegundoNode);
		nodeSocket.send(sendPacket);
		//System.out.println("Enviado!");
		//nodeSocket.close();
	}

	void receber(int numNode) throws IOException {
		byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
		nodeSocket.receive(receivePacket);
		String texto = new String(receivePacket.getData());
		System.out.println("Cliente "+numNode+": "+texto);
		//nodeSocket.close();
	}
}


class Enviar extends Thread{
	private Node node;
	InetAddress segundoNodeIP;
	int portaSegundoNode;
	String texto;

	public Enviar(Node node, InetAddress segundoNodeIP, int portaSegundoNode, String texto) throws IOException {
		this.node = node;
		this.segundoNodeIP = segundoNodeIP;
		this.portaSegundoNode = portaSegundoNode;
		this.texto = texto;
	}

	public void run(){
		try {
			node.enviar(segundoNodeIP, portaSegundoNode, texto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


class Receber extends Thread{
	private Node node;
	int numNode;

	public Receber(Node node, int numNode) {
		this.node = node;
		this.numNode = numNode;
	}

	public void run() {
		try {
			node.receber(numNode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}