package negocinho;

import java.net.SocketException;
import java.net.UnknownHostException;
import negocinho.*;

public class Testando {
	public static void main(String[] args) throws UnknownHostException, SocketException {
		Cliente A = new Cliente(4999, "localhost", 5001);
		
		Thread rcvA = new Reception(A);
		Thread sndA = new Emission(A);
		
		rcvA.start();
		sndA.start();
	}
}
