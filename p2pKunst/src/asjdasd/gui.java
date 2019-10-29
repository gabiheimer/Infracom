package asjdasd;

import org.w3c.dom.css.RGBColor;

import javax.security.auth.callback.TextInputCallback;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class gui {
    JPanel painel;
    JTextArea mensagens;
    JTextField output;
    JButton send;

    public gui(Node node, InetAddress segundoNodeIP, int portaCliente2){
        mensagens.setEditable(false);
        mensagens.setLineWrap(true);

        send.setBackground(new Color(0, 142, 204));
        send.setForeground(Color.WHITE);

        JFrame janela = new JFrame();
        janela.setContentPane(painel);
        janela.setVisible(true);
        janela.pack();
        janela.setSize(540,540);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensagens.append(output.getText()+"\n");
                try {
                    byte[] out = output.getText().getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(out, out.length, segundoNodeIP, portaCliente2);
                    node.nodeSocket.send(sendPacket);
                    output.setText(null);
                }
                catch (SocketException exception) {
                    exception.printStackTrace(); }
                catch (IOException e1) {
                    e1.printStackTrace(); }
            }
        });
        output.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && !output.getText().isEmpty()){
                    mensagens.append(output.getText()+"\n");
                    try {
                        byte[] out = output.getText().getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(out, out.length, segundoNodeIP, portaCliente2);
                        node.nodeSocket.send(sendPacket);
                        output.setText(null);
                    }
                    catch (SocketException exception) {
                        exception.printStackTrace(); }
                    catch (IOException e1) {
                        e1.printStackTrace(); }
                }
            }
        });
    }

    void ReceberMsg (String text) {
        mensagens.append(text+"\n");
    }
}
