package com.uic.ids520;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class Frame1 extends Thread{

	static JFrame frame;
	private static JTextField textField;
	private static JPasswordField passwordField;
	static String userName;
	static String password;
	private static Socket clientSocket;
	static UserBean userBean;

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Frame1(Socket clientSocket) throws IOException {
		Frame1.clientSocket=clientSocket;
		System.out.println("hello");
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private static void initialize() throws IOException {
		frame = new JFrame();
		frame.setTitle("Course Registration- Spring 2017");
		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.setBackground(new Color(204, 102, 153));
		userBean=new UserBean();
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try{ 
					userName=textField.getText();
					System.out.println(userName);
					password=passwordField.getText();
					if(userName.equals("admin") && password.equals("admin")){
						ServerSideViews s = new ServerSideViews();
						s.setVisible(true);
						s.setUserName(userName);
						s.setFrameLogin(frame);
						frame.setVisible(false);						
					}
					else if(!(userName.isEmpty()) && !(userName.equals("admin"))){
						userBean.setUserName(userName);
						userBean.setPassword(password);
						userBean.setPageName("login");
						ObjectOutputStream oos=new ObjectOutputStream(clientSocket.getOutputStream());
						oos.writeObject(userBean);
						oos.flush();
						ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
						userBean=(UserBean) ois.readObject();
						if(userBean.getFlag()==true){
						System.out.println("return"+userBean.getMajor());
						if(userBean.getLoginFlag()==0){
						JOptionPane.showMessageDialog(null, "Welcome " + userBean.getUserName() );
						CourseRegistration c = new CourseRegistration(userBean,clientSocket);
						c.setVisible(true);
						c.setFrameLogin(frame);
						frame.setVisible(false);
						String major="";
						String course1="";
						String course2="";
						String course3="";
						String course4="";
						String course5="";
						major=userBean.getMajor();
						c.setUserName(userName);
						c.comboBox.setSelectedItem(major);
						c.comboBox.disable();
						course1=userBean.getCourse0();
						course2=userBean.getCourse1();
						course3=userBean.getCourse2();
						course4=userBean.getCourse3();
						course5=userBean.getCourse4();
						
							if(course1==null && course2==null && course3==null && course4==null && course5 ==null )
							{
								JOptionPane.showMessageDialog(null, "You have not registered for any courses");				
							}
							else{ 
								if(course1==null)
									course1=" ";
								if(course2==null)
									course2=" ";
								if(course3==null)
									course3=" ";
								if(course4==null)
									course4=" ";
								if(course5==null)
									course5=" "; 
								JOptionPane.showMessageDialog(null, "You are registered for the courses: " + "\n"+course1 + "\n"+ course2 + "\n" + course3 + "\n" + course4 + "\n" +course5);
							}	
						}
						else{
							JOptionPane.showMessageDialog(null, "User already logged in");
						}
						}else{
							JOptionPane.showMessageDialog(null, "Invalid user name or password");			
						}
					}
					
				}catch(Exception e1){
						e1.printStackTrace();
				}
				
			}
		});
		
		
		btnNewButton.setBounds(157, 182, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(76, 96, 86, 16);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(86, 129, 61, 16);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(157, 91, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.setBackground(new Color(51, 102, 204));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegistration reg = new UserRegistration(clientSocket,userBean);	
				reg.setVisible(true);
				reg.setFrameLogin(frame);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(157, 225, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(157, 124, 130, 26);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Course Registration - Spring 2017");
		lblNewLabel.setBounds(109, 24, 241, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}