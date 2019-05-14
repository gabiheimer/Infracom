package negocinho;

import java.net.SocketException;
import java.net.UnknownHostException;
import negocinho.*;

public class TestandoB {
	public static void main(String[] args) throws UnknownHostException, SocketException {
		// argumentos sao: socket do cliente B, IP do cliente A, socket do cliente A
		Cliente B = new Cliente(5001, "localhost", 4999);
		
		Thread rcvB = new Reception(B);
		Thread sndB = new Emission(B);
		
		rcvB.start();
		sndB.start();
	}
}
