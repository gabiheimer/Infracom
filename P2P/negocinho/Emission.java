package negocinho;

import java.io.IOException;
import negocinho.*;

public class Emission extends Thread {
	private Cliente cliente;
	
	public Emission(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void run() {
		try {
			cliente.send();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
