package negocinho;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	Scanner in = new Scanner(System.in);
	public DatagramSocket mySocket;
	public byte[] rcvData = new byte[1024], sendData;
	public InetAddress otherClientIP;
	public DatagramPacket rcvPacket, sendPacket;
	public String messageRcv, messageSend, msg;
	public int otherClientSocket;
	
	public Cliente(int mySocket, String otherClientIp, int otherClientSocket) throws UnknownHostException, SocketException {
		this.mySocket = new DatagramSocket(mySocket);
		this.otherClientSocket = otherClientSocket;
		this.otherClientIP = InetAddress.getByName(otherClientIp);
	}
	
	void receive() throws IOException {
		while(true) {
			rcvData = new byte[1024];
			rcvPacket = new DatagramPacket(rcvData, rcvData.length);
			mySocket.receive(rcvPacket);
			messageRcv = new String(rcvPacket.getData(), "UTF-8");
			System.out.println("Mensagem recebida: " + messageRcv);
		}
	}
	
	void send() throws IOException {
		while(true) {
			msg = in.nextLine();
			messageSend = msg;
			sendData = (messageSend).getBytes();
			sendPacket = new DatagramPacket(sendData, sendData.length, otherClientIP, otherClientSocket);
			mySocket.send(sendPacket);
		}
	}
}