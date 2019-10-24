package chat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import chat.*;

public class Rcv_Thread extends Thread{
	private Client cliente;
	
	public Rcv_Thread(Client cliente) {
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