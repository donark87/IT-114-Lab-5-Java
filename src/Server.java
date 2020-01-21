import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Server extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldServer;
	private static JTextArea textAreaServer;
	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream dataOutput;
	static DataInputStream dataInput;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
									
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
		String messageIn = "";
		serverSocket = new ServerSocket(1201);
		socket = serverSocket.accept();
		dataInput = new DataInputStream(socket.getInputStream());
		dataOutput = new DataOutputStream(socket.getOutputStream());
		while(!messageIn.equals("Stop")) {
			messageIn = dataInput.readUTF();
			
			textAreaServer.setText(textAreaServer.getText() + "\n Client: " + messageIn);
		}
		}catch (IOException e) {
			System.out.println(e);
		}
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textAreaServer = new JTextArea();
		textAreaServer.setAlignmentX(Component.LEFT_ALIGNMENT);
		textAreaServer.setBounds(10, 11, 414, 214);
		contentPane.add(textAreaServer);
		textAreaServer.setEditable(false);
		
		
		
		textFieldServer = new JTextField();
		textFieldServer.setBounds(10, 236, 315, 20);
		contentPane.add(textFieldServer);
		textFieldServer.setColumns(10);
		
		JButton btnSendServer = new JButton("Send");
		btnSendServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String message = "";
				message = textFieldServer.getText();
				dataOutput.writeUTF(message);
				textAreaServer.append("\n Server: " + message);
				textFieldServer.setText("");
				}catch(Exception e1) {
					System.out.println(e1);
					
				}
			}
		});
		btnSendServer.setBounds(335, 236, 89, 23);
		contentPane.add(btnSendServer);
		
		
	}
	

}
