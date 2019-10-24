package chat;

import java.net.SocketException;
import java.net.UnknownHostException;
import chat.*;
import java.io.IOException;

public class T1{
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
		// argumentos sao: socket do cliente A, IP do cliente B, socket do cliente B
        Client A = new Client("localhost");
        A.connectToServer("localhost", 6789);
	}
}