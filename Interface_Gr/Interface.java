import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.*;

public class Interface {
	static JButton merda = new JButton("Enviar!");
	static JTextField textField = new JTextField();
	static JTextArea out = new JTextArea();
	static JPanel painel = new JPanel();

	public Interface(Node node, InetAddress segundoNodeIP, int portaCliente2){
		out.setFont(new Font("Arial", Font.PLAIN, 15)); 
		out.setSize(300, 400);
		out.setLineWrap(true); 
		out.setEditable(false); 
		textField.setBounds(128, 28, 86, 20);
		painel.add(out);
		painel.add(textField);
		painel.add(merda);
		textField.setColumns(10);
		JFrame janela = new JFrame();
		janela.add(painel);
		janela.setVisible(true);
		janela.pack();
		janela.setSize(540,540);
		merda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.append(textField.getText()+"\n");
				 try {
					 byte[] out = textField.getText().getBytes();
					 DatagramPacket sendPacket = new DatagramPacket(out, out.length, segundoNodeIP, portaCliente2);
					 node.nodeSocket.send(sendPacket);
					 textField.setText(null);
				 }
				 catch (SocketException exception) { 
					 exception.printStackTrace(); }
				 catch (IOException e1) { 
					 e1.printStackTrace(); }
			}
		});	
	}
	
	void ReceberMsg (String text) {
		out.append(text+"\n");
	}
}

