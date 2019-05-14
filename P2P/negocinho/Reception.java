package negocinho;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import negocinho.*;

public class Reception extends Thread{
	private Cliente cliente;
	
	public Reception(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void run() {
		try {
			cliente.receive();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
