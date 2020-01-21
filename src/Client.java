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
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldClient;
	private static JTextArea textAreaClient;
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
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			String messageIn = "";
			socket = new Socket("127.0.0.1",1201);
			dataInput = new DataInputStream(socket.getInputStream());
			dataOutput = new DataOutputStream(socket.getOutputStream());
			
			while(!messageIn.equals("Stop")) {
				messageIn = dataInput.readUTF();
				
				textAreaClient.setText(textAreaClient.getText() + "\n Server: " + messageIn);
			}
			}catch (IOException e) {
				System.out.println(e);
			}
		
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textAreaClient = new JTextArea();
		textAreaClient.setAlignmentX(0.0f);
		textAreaClient.setBounds(10, 11, 414, 214);
		contentPane.add(textAreaClient);
		textAreaClient.setEditable(false);
		
		textFieldClient = new JTextField();
		textFieldClient.setColumns(10);
		textFieldClient.setBounds(10, 236, 315, 20);
		contentPane.add(textFieldClient);
		
		JButton btnSendClient = new JButton("Send");
		btnSendClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = "";
					message = textFieldClient.getText();
					dataOutput.writeUTF(message);
					textAreaClient.append("\n Client: " + message);
					textFieldClient.setText("");
					}catch(Exception e1) {
						System.out.println(e1);
						
					}
			}
		});
		btnSendClient.setBounds(335, 236, 89, 23);
		contentPane.add(btnSendClient);
		
		
	}
}
