package chat;

import java.io.IOException;
import chat.*;

public class Send_Thread extends Thread {
	private Client cliente;
	
	public Send_Thread(Client cliente) {
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