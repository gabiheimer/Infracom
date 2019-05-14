package negocinho;

import java.net.SocketException;
import java.net.UnknownHostException;
import negocinho.*;

public class Testando {
	public static void main(String[] args) throws UnknownHostException, SocketException {
		// argumentos sao: socket do cliente A, IP do cliente B, socket do cliente B
		Cliente A = new Cliente(4999, "localhost", 5001);
		
		Thread rcvA = new Reception(A);
		Thread sndA = new Emission(A);
		
		rcvA.start();
		sndA.start();
	}
}
