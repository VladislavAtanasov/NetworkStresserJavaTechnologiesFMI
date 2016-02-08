package network.stresser;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class StresserGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private String request;
	private String host;
	private int port;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StresserGUI frame = new StresserGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StresserGUI() {
		setTitle("Network Stresser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 257);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblServer = new JLabel("Server*: ");
		lblServer.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblServer.setBounds(91, 47, 56, 16);
		contentPane.add(lblServer);

		JLabel lblHost = new JLabel("Host*:");
		lblHost.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblHost.setBounds(91, 76, 56, 16);
		contentPane.add(lblHost);

		JLabel lblPort = new JLabel("Port*:");
		lblPort.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblPort.setBounds(91, 105, 56, 16);
		contentPane.add(lblPort);

		textField = new JTextField();
		textField.setBounds(157, 44, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(157, 73, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(157, 108, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JRadioButton rdbtnGet = new JRadioButton("GET");
		rdbtnGet.setBackground(Color.WHITE);
		rdbtnGet.setSelected(true);
		buttonGroup.add(rdbtnGet);
		rdbtnGet.setBounds(351, 43, 127, 25);
		contentPane.add(rdbtnGet);

		JRadioButton rdbtnPost = new JRadioButton("POST");
		rdbtnPost.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnPost);
		rdbtnPost.setBounds(351, 72, 127, 25);
		contentPane.add(rdbtnPost);

		JButton btnStressThisAddress = new JButton("Stress this address!");
		btnStressThisAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("")
						|| textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please, fill the required fields.");
					return;
				}

				if (textField_2.getText().startsWith("-")) {
					JOptionPane.showMessageDialog(null, "Port cannot have a negative value.");
					return;
				}
				request = textField.getText();
				host = "Host: " + textField_1.getText();
				try {
					port = Integer.parseInt(textField_2.getText());
				} catch (NumberFormatException exc) {
					JOptionPane.showMessageDialog(null, "Invalid port.");
					return;
				}
				if (rdbtnGet.isSelected()) {
					request = rdbtnGet.getText() + " " + request;
				} else if (rdbtnPost.isSelected()) {
					request = rdbtnPost.getText() + " " + request;
				}

				btnStressThisAddress.setBackground(Color.GREEN);
				try {
					NetworkStresserStarter starter = new NetworkStresserStarter(request, host, port);
					String[] result = starter.startStresserGui().split("\n");
					textField_3.setText(result[0]);
					textField_4.setText(result[1]);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		});

		btnStressThisAddress.setBounds(91, 163, 181, 25);
		contentPane.add(btnStressThisAddress);

		JLabel lblMaximumSuccessfulRequests = new JLabel("Maximum Successful Requests:");
		lblMaximumSuccessfulRequests.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblMaximumSuccessfulRequests.setBackground(Color.WHITE);
		lblMaximumSuccessfulRequests.setBounds(351, 123, 196, 16);
		contentPane.add(lblMaximumSuccessfulRequests);

		JLabel lblMaximumTimeFor = new JLabel("Maximum time for response:");
		lblMaximumTimeFor.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblMaximumTimeFor.setBounds(351, 166, 196, 16);
		contentPane.add(lblMaximumTimeFor);

		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(570, 121, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setBounds(570, 164, 116, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\Hack Bulgaria\\Algorithms\\JavaWindows\\JTCourseProject\\resources\\net.jpg"));
		label.setBounds(0, 0, 744, 363);
		contentPane.add(label);

		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { lblServer, lblPort, textField,
				textField_1, textField_2, rdbtnGet, rdbtnPost, btnStressThisAddress, lblMaximumSuccessfulRequests,
				lblMaximumTimeFor, lblHost, textField_3, textField_4 }));
	}
}
