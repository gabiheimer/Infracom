import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente1 {

	public static void main(String[] args) throws IOException, InterruptedException{
		Scanner teclado = new Scanner(System.in);
		Socket clienteSocket = new Socket("localhost", 3003);
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
		int portaCliente2 = Integer.parseInt(inFromServer.readLine());
		clienteSocket.close();
		Node u1 = new Node(InetAddress.getByName("localhost"),3003,portaCliente2);
		Interface interfaces = new Interface(u1, InetAddress.getByName("localhost"), portaCliente2);
		Thread r1 = new Receber(u1,2, interfaces);
		r1.start();

	}

}

class Node {
	int portaNode;
	int portaSegundoNode;
	DatagramSocket nodeSocket;

	public Node(InetAddress nodeIP, int portaNode, int portaSegundoNode) throws IOException{
		this.portaNode = portaNode;
		this.portaSegundoNode = portaSegundoNode;
		nodeSocket = new DatagramSocket(portaNode);
	}

	void enviar(InetAddress segundoNodeIP, int portaSegundoNode, Interface interfaces) throws IOException {
		byte[] out = interfaces.textField.getText().getBytes();
		DatagramPacket sendPacket = new DatagramPacket(out, out.length, segundoNodeIP, portaSegundoNode);
		nodeSocket.send(sendPacket);
		interfaces.textField.setText(null);
		//System.out.println("Enviado!");
	}

	void receber(int numNode, Interface interfaces) throws IOException {
		while (true) {
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			nodeSocket.receive(receivePacket);
			String texto = new String(receivePacket.getData());
			texto = texto.trim();
			interfaces.ReceberMsg(texto);
			System.out.println(numNode+ ": " +texto);
		}
	}
}


class Enviar extends Thread{
	private Node node;
	InetAddress segundoNodeIP;
	int portaSegundoNode;
	Interface interfaces;

	public Enviar(Node node, InetAddress segundoNodeIP, int portaSegundoNode, Interface interfaces) throws IOException {
		this.node = node;
		this.segundoNodeIP = segundoNodeIP;
		this.portaSegundoNode = portaSegundoNode;
		this.interfaces = interfaces;
	}

	public void run(){
		try {
			node.enviar(segundoNodeIP, portaSegundoNode, interfaces);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


class Receber extends Thread{
	private Node node;
	int numNode;
	Interface interfaces;

	public Receber(Node node, int numNode, Interface interfaces) {
		this.node = node;
		this.numNode = numNode;
		this.interfaces = interfaces;
	}

	public void run() {
		try {
			node.receber(numNode, interfaces);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}