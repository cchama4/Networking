package com.uic.ids520;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserRegistration extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JPasswordField passwordField_1;
	private JComboBox comboBox;
	private String x="Accounting";
	private static UserBean userBean=new UserBean();
	private static Socket clientSocket;

	Connection conn = null;
	PreparedStatement pst = null;
	private JFrame frameLogin;
	public void setFrameLogin(JFrame frameLogin) {
		this.frameLogin = frameLogin;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegistration frame = new UserRegistration(clientSocket, userBean);
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
	public UserRegistration(Socket clientSocket, UserBean userBean) {
		UserRegistration.userBean=userBean;
		UserRegistration.clientSocket=clientSocket;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocation(100,100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(6, 40, 85, 16);
		contentPane.add(lblNewLabel);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(6, 71, 85, 16);
		contentPane.add(lblLastName);

		JLabel lblMajor = new JLabel("Major");
		lblMajor.setBounds(6, 99, 85, 16);
		contentPane.add(lblMajor);

		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setBounds(6, 127, 85, 16);
		contentPane.add(lblUserId);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(6, 155, 85, 16);
		contentPane.add(lblPassword);

		textField = new JTextField();
		textField.setBounds(103, 35, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(103, 66, 130, 26);
		contentPane.add(textField_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(103, 122, 130, 26);
		contentPane.add(textField_3);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_3.getText().length()>12 || textField_3.getText().length()<6 )
				{
					//textField_3.setText(textField_3.getText().substring(0, 11));
					JOptionPane.showMessageDialog(null, "User name and password should be between 6 to 12 characters.");
				}
				else if(passwordField_1.getText().length()>12 || passwordField_1.getText().length()<6 )
				{
					//textField_3.setText(textField_3.getText().substring(0, 11));
					JOptionPane.showMessageDialog(null, "User name and password should be between 6 to 12 characters.");
				}
				else if(textField_1.getText().length() == 0 || textField.getText().length() == 0 ){
					JOptionPane.showMessageDialog(null, "Fist name and last name are mandatory fields.");
				}
				else{
					userBean.setFirstNaame(textField.getText());
					userBean.setLastName(textField_1.getText());
					userBean.setMajor(x);
					userBean.setUserName(textField_3.getText());
					userBean.setPassword(passwordField_1.getText());
					userBean.setPageName("UserRegistration");
					try {
						System.out.println("again");
						ObjectOutputStream oos=new ObjectOutputStream(UserRegistration.clientSocket.getOutputStream());
						oos.writeObject(userBean);
						oos.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						ObjectInputStream ois = new ObjectInputStream(UserRegistration.clientSocket.getInputStream());
				    	UserBean ob= (UserBean) ois.readObject();
				    	System.out.println(ob.getDuplicateFlag()+""+ob.getUserName());
				    	if(ob.getDuplicateFlag()){
				    		setVisible(false);
							JOptionPane.showMessageDialog(null, "Account created successfully");
							frameLogin.setVisible(true);
				    	}
				    	else{
				    		JOptionPane.showMessageDialog(null, "User Id already exists");
				    	}
					}catch(Exception e1){
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnNewButton.setBounds(155, 216, 117, 29);
		contentPane.add(btnNewButton);

		JLabel lblUserRegistrationDetails = new JLabel("User Registration Details");
		lblUserRegistrationDetails.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 16));
		lblUserRegistrationDetails.setBounds(155, 7, 262, 16);
		contentPane.add(lblUserRegistrationDetails);

		JList list = new JList();
		list.setBounds(137, 127, 1, 1);
		contentPane.add(list);

		JList list_1 = new JList();
		list_1.setBounds(118, 122, 108, -23);
		contentPane.add(list_1);

		JList list_2 = new JList();
		list_2.setBounds(325, 185, 79, -70);
		contentPane.add(list_2);

		JList list_3 = new JList();
		list_3.setBackground(Color.WHITE);
		list_3.setBounds(338, 151, 79, -23);
		contentPane.add(list_3);

		JList list_4 = new JList();
		list_4.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Accounting"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_4.setBounds(335, 151, 57, -23);
		contentPane.add(list_4);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(103, 150, 130, 26);
		contentPane.add(passwordField_1);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x = comboBox.getSelectedItem().toString();
				System.out.println(x);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Accounting", "Finance", "Marketing"}));
		comboBox.setBounds(103, 95, 130, 27);
		contentPane.add(comboBox);
	}
}
